package com.challenge.restaurant.Restaurant.service;

import com.challenge.restaurant.Restaurant.exception.IllegalOrderException;
import com.challenge.restaurant.Restaurant.model.Order;
import com.challenge.restaurant.Restaurant.model.OrderStatus;
import com.challenge.restaurant.Restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by sachin on 4/7/19.
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(Order order){

        if (order == null || order.getItemName()==null)
            throw new IllegalOrderException("Invalid order!!");
        order.setOrderPlacedTime(new Date());
        order.setStatus(OrderStatus.PLACED.getText());

        return orderRepository.save(order);
    }


    public Order searchOrder(Long id){
        return orderRepository.findOrderById(id);
    }


    public Order updateStatus(Order order) {

        Order orderFound= searchOrder(order.getId());
        try {
            OrderStatus status = OrderStatus.valueOf(order.getStatus());
            if (Arrays.asList(OrderStatus.values()).contains(status)){
                orderFound.setStatus(status.getText());
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalOrderException("invalid order status");

        }


        orderRepository.save(orderFound);

        return order;
    }
}
