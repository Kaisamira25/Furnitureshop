package com.poly.dto;

import com.poly.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String address;
    private double total;
    List<OrderDetail> orderDetailList;
}
