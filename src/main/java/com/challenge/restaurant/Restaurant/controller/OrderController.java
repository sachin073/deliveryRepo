package com.challenge.restaurant.Restaurant.controller;

import com.challenge.restaurant.Restaurant.model.DeliveryPerson;
import com.challenge.restaurant.Restaurant.model.ErrorRespose;
import com.challenge.restaurant.Restaurant.model.Order;
import com.challenge.restaurant.Restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sachin on 4/7/19.
 */
@RestController("restaurant/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    final String ORDER_NOT_FOUND_MSG="No order exist by id ";


    @PostMapping(path = "/place")
    public ResponseEntity<Long> placeorder(@RequestBody Order order){
        Order placedOrder=null;
        try {
            placedOrder=  orderService.createOrder(order);
        } catch (Exception e) {
            new ResponseEntity<ErrorRespose>(new ErrorRespose(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Long>(order.getId(), HttpStatus.OK);
    }


    @GetMapping(path = "/getStatus/{id}")
    public ResponseEntity<Order> getStatus(@PathVariable("id") Long id){
        Order placedOrder=null;
        try {
            placedOrder=  orderService.searchOrder(id);
            if (placedOrder==null){
                new ResponseEntity<ErrorRespose>(new ErrorRespose(ORDER_NOT_FOUND_MSG+id),HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            new ResponseEntity<ErrorRespose>(new ErrorRespose(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Order>(placedOrder, HttpStatus.OK);
    }


    @PutMapping(path = "/updateStatus")
    public ResponseEntity<Order> updateStatus(@RequestBody Order order){
        Order placedOrder=null;
        try {
            placedOrder=  orderService.updateStatus(order);

        } catch (Exception e) {
            new ResponseEntity<ErrorRespose>(new ErrorRespose(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Order>(placedOrder, HttpStatus.OK);
    }

    @GetMapping(path = "/getDeliveryReport")
    public ResponseEntity<DeliveryPerson> getDeliveryReport(){
        DeliveryPerson placedOrder=null;
        try {

        } catch (Exception e) {
            new ResponseEntity<ErrorRespose>(new ErrorRespose(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return null;
    }

}
