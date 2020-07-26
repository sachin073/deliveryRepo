package com.challenge.restaurant.service;

import com.challenge.restaurant.controller.OrderController;
import com.challenge.restaurant.exception.IllegalOrderException;
import com.challenge.restaurant.model.ErrorRespose;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.model.OrderStatus;
import com.challenge.restaurant.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by sachin on 4/7/19.
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order createOrder(Order order) {

		if (order == null || order.getItemName() == null)
			throw new IllegalOrderException("Invalid order!!");
		order.setOrderPlacedTime(new Date());
		order.setStatus(OrderStatus.PLACED.getText());

		return orderRepository.save(order);
	}

	@Override
	public Order searchOrder(long id) {
		Order order = orderRepository.findOrderById(id);
		if (order == null) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(OrderController.ORDER_NOT_FOUND_MSG + id), HttpStatus.NOT_FOUND);
		}
		return order;
	}

	@Override
	public void deleteOrder(long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public Order updateStatus(long id, OrderStatus status) {

		Order orderFound = searchOrder(id);
		orderFound.setStatus(status.getText());

		orderRepository.save(orderFound);

		return orderFound;
	}
}