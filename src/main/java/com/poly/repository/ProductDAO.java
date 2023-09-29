package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
//    @Query(nativeQuery = true, value = "select * from products where products.category_id = ?1")
//    @Query(value="select o from Product o where o.category.id = ?1")
//    List<Product> findAllByCategoryIDLikeID(Integer id);
    List<Product> findAllByCategory_Id(Integer id);
    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);

}
