package com.poly.api;

import com.poly.model.Product;
import com.poly.payload.response.APIResponse;
import com.poly.payload.response.CloudinaryResponse;
import com.poly.payload.response.FailureAPIResponse;
import com.poly.payload.response.SuccessAPIResponse;
import com.poly.repository.ProductDAO;
import com.poly.serviceImpl.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ProductApi {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/public/products")
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productDAO.findAll());
    }

    @GetMapping("/public/products/category-{id}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productDAO.findAllByCategory_Id(id));
    }

    @GetMapping("/public/products/search")
    public ResponseEntity<?>  productsByPrice( @RequestParam("min") Integer min, @RequestParam("max") Integer max) {
        return ResponseEntity.ok(productDAO.findByPriceBetween(min, max));
    }


    @PostMapping("/admin/products")
    public ResponseEntity<?> createProduct(@RequestPart(name = "product") Product product,
                                           @RequestPart(name="image") MultipartFile image){
        if (image != null) {
            CloudinaryResponse cloudinaryResponse = cloudinaryService.uploadFile(image, "product");
            product.setImage(cloudinaryResponse.getUrl());
        }
        product = productDAO.save(product);
        APIResponse response = new SuccessAPIResponse(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/admin/products")
    public ResponseEntity<?> updateProduct(@RequestPart(name = "product") Product product,
                                           @RequestPart(name="image") @Nullable MultipartFile image){
        if(product == null){
            APIResponse response = new FailureAPIResponse("Product id is required!");
        }
        Product exists = productDAO.findById(product.getId()).orElse(null);
        if(exists == null){
            APIResponse response = new FailureAPIResponse("Cannot find product with id: "+product.getId());
        }
        if (image != null) {
            CloudinaryResponse cloudinaryResponse = cloudinaryService.uploadFile(image, "product");
            product.setImage(cloudinaryResponse.getUrl());
        }
        product = productDAO.save(product);
        APIResponse response = new SuccessAPIResponse(product);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        APIResponse response = null;
        try {
            productDAO.deleteById(id);
            response = new SuccessAPIResponse("Delete successfully!");
        } catch (Exception ex) {
            response = new FailureAPIResponse(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
