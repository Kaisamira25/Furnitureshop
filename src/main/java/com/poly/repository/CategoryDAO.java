package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.model.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
    @Query(nativeQuery = true, value = "select * from categories where id in (select c.id from categories c join products p on c.id = p.category_id group by c.id)")
    List<Category> getExistCategory();
}
