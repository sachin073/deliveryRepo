package com.challenge.restaurant.controller;

import com.challenge.restaurant.model.DeliveryPerson;
import com.challenge.restaurant.model.ErrorRespose;
import com.challenge.restaurant.model.Order;
import com.challenge.restaurant.model.OrderStatus;
import com.challenge.restaurant.request.model.OrderRequest;
import com.challenge.restaurant.response.mapper.OrderMapperService;
import com.challenge.restaurant.response.model.OrderDetailsResponse;
import com.challenge.restaurant.service.OrderService;
import com.challenge.restaurant.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sachin on 4/7/19.
 */
@RestController
@RequestMapping("/restaurant")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	PersonService deliveryPersonService;

	@Autowired
	OrderMapperService mapperService;

	public static final String ORDER_NOT_FOUND_MSG = "No order exist by id ";

	@PostMapping(path = "/order/place")
	public ResponseEntity<String> placeorder(@RequestBody OrderRequest order) {

		Order placedOrder = new Order(order.getItemName());

		orderService.createOrder(placedOrder);

		return new ResponseEntity<String>(mapperService.mapOrderIdentifier(placedOrder), HttpStatus.OK);
	}

	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderDetailsResponse> getStatus(@PathVariable("id") String id) {
		String orderSubs = id.substring(4);
		long oId = 0l;
		try {
			oId = Long.valueOf(orderSubs);
		} catch (NumberFormatException e) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(ORDER_NOT_FOUND_MSG + id), HttpStatus.NOT_FOUND);
		}

		Order placedOrder = orderService.searchOrder(oId);

		if (placedOrder == null) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(ORDER_NOT_FOUND_MSG + id), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(mapperService.mapOrderDetails(placedOrder), HttpStatus.OK);
	}

	@DeleteMapping(value = "/order/{id}")
	public ResponseEntity<OrderDetailsResponse> deleteOrder(@PathVariable("id") String id) {

		String orderSubs = id.substring(4);
		long oId = 0l;
		try {
			oId = Long.valueOf(orderSubs);
		} catch (NumberFormatException e) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(ORDER_NOT_FOUND_MSG + id), HttpStatus.NOT_FOUND);
		}

		Order placedOrder = orderService.searchOrder(oId);
		if (placedOrder == null) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(ORDER_NOT_FOUND_MSG + id), HttpStatus.NOT_FOUND);
		}
		orderService.deleteOrder(oId);

		return new ResponseEntity<>(mapperService.mapOrderDetails(placedOrder), HttpStatus.OK);
	}

	@PutMapping(path = "/order/{id}/status/{status}")
	public ResponseEntity<OrderDetailsResponse> updateStatus(@PathVariable String id,
			@PathVariable OrderStatus status) {

		String orderSubs = id.substring(4);
		long oId = 0l;
		try {
			oId = Long.valueOf(orderSubs);
		} catch (NumberFormatException e) {
			new ResponseEntity<ErrorRespose>(new ErrorRespose(ORDER_NOT_FOUND_MSG + id), HttpStatus.NOT_FOUND);
		}

		Order placedOrder = orderService.updateStatus(oId,status);

		return new ResponseEntity<>(mapperService.mapOrderDetails(placedOrder), HttpStatus.OK);
	}

	@GetMapping(path = "/order/getDeliveryReport")
	public ResponseEntity<List<DeliveryPerson>> getDeliveryReport() {
		List<DeliveryPerson> personList = null;
		personList = deliveryPersonService.getAllActive();

		return new ResponseEntity<>(personList, HttpStatus.OK);
	}

}
