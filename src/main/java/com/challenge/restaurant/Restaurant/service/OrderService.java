package com.challenge.restaurant.Restaurant.service;

import com.challenge.restaurant.Restaurant.exception.IllegalOrderException;
import com.challenge.restaurant.Restaurant.model.Order;
import com.challenge.restaurant.Restaurant.model.OrderStatus;
import com.challenge.restaurant.Restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

        order.setStatus(OrderStatus.PLACED.name());
        order.setPerson(null);

        return orderRepository.save(order);
    }


    public Order searchOrder(Long id){
        return orderRepository.findOrderById(id);
    }


    public Order updateStatus(Order order) {

        Order orderFound= searchOrder(order.getId());

        if (OrderStatus.BAKING.name().equals(order.getStatus())){
            order.setStatus(OrderStatus.BAKING.name());
        }else if (OrderStatus.PLACED.name().equals(order.getStatus())){
            order.setStatus(OrderStatus.PLACED.name());
        }else if (OrderStatus.READY_TO_DELIVER.name().equals(order.getStatus())){
            order.setStatus(OrderStatus.READY_TO_DELIVER.name());
        }else {
            throw new IllegalOrderException("Invalid status");
        }

        orderRepository.save(orderFound);

        return order;
    }
}
