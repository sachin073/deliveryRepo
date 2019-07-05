package com.challenge.restaurant.Restaurant.repository;

import com.challenge.restaurant.Restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sachin on 4/7/19.
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findOrderById(Long id);
}
