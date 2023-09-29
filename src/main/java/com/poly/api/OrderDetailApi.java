package com.poly.api;

import com.poly.repository.OrderDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oderDetails")
public class OrderDetailApi {
    @Autowired
    OrderDetailDAO orderDetailDAO;

    @PostMapping("")
    public ResponseEntity<?> createOderDetail(@RequestBody Object products) {
        return ResponseEntity.ok(products);
    }

}
