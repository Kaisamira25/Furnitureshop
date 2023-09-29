package com.poly.api;

import com.poly.dto.OrderDto;
import com.poly.model.Account;
import com.poly.model.Order;
import com.poly.model.OrderDetail;
import com.poly.payload.response.APIResponse;
import com.poly.repository.OrderDAO;
import com.poly.security.CurrentUser;
import com.poly.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderApi {
    @Autowired
    OrderDAO orderDAO;

    @PostMapping("/api/checkout")
    public ResponseEntity<?> checkOut(@CurrentUser UserPrincipal userPrincipal, @RequestBody Order order){
        Account account = userPrincipal.toAccount();
        for(OrderDetail orderDetail : order.getOrderDetailList()){
            orderDetail.setOrders(order);
        }
        order.setAccount(account);
        orderDAO.save(order);
        return ResponseEntity.ok(new APIResponse("Success"));
    }

    @GetMapping("/api/orders")
    public ResponseEntity<?> getOrders(@CurrentUser UserPrincipal userPrincipal){
        List<Order> orders = orderDAO.findByAccount(userPrincipal.toAccount());
        return ResponseEntity.ok(orders);
    }
}
