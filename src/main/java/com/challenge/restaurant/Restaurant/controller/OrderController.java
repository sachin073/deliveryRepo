package com.challenge.restaurant.Restaurant.controller;

import com.challenge.restaurant.Restaurant.model.DeliveryPerson;
import com.challenge.restaurant.Restaurant.model.ErrorRespose;
import com.challenge.restaurant.Restaurant.model.Order;
import com.challenge.restaurant.Restaurant.service.DeliveryPersonService;
import com.challenge.restaurant.Restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sachin on 4/7/19.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DeliveryPersonService deliveryPersonService;


    final String ORDER_NOT_FOUND_MSG="No order exist by id ";


    @PostMapping(path = "/restaurant/order/place")
    public ResponseEntity<Long> placeorder(@RequestBody Order order){
        Order placedOrder=null;
            placedOrder=  orderService.createOrder(order);


        return new ResponseEntity<Long>(order.getId(), HttpStatus.OK);
    }


    @RequestMapping(value = "/restaurant/order/getStatus/{id}",method = RequestMethod.GET)
    public ResponseEntity<Order> getStatus(@PathVariable("id") Long id){
        Order placedOrder=null;
            placedOrder=  orderService.searchOrder(id);
            if (placedOrder==null){
                new ResponseEntity<ErrorRespose>(new ErrorRespose(ORDER_NOT_FOUND_MSG+id),HttpStatus.NOT_FOUND);
            }

        return new ResponseEntity<Order>(placedOrder, HttpStatus.OK);
    }


    @PutMapping(path = "/restaurant/order/updateStatus")
    public ResponseEntity<Order> updateStatus(@RequestBody Order order){
        Order placedOrder=null;
            placedOrder=  orderService.updateStatus(order);

        return new ResponseEntity<Order>(placedOrder, HttpStatus.OK);
    }

    @GetMapping(path = "/restaurant/order/getDeliveryReport")
    public ResponseEntity<List<DeliveryPerson>> getDeliveryReport(){
        List<DeliveryPerson> personList=null;
               personList= deliveryPersonService.getAllActive();

        return new ResponseEntity<>(personList,HttpStatus.OK);
    }

}
