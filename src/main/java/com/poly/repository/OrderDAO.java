package com.poly.repository;

import com.poly.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Order;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    List<Order> findByAccount(Account account);
}
