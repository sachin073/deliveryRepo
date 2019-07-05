package com.challenge.restaurant.Restaurant.controller;


import com.challenge.restaurant.Restaurant.model.DeliveryPerson;
import com.challenge.restaurant.Restaurant.model.Order;
import com.challenge.restaurant.Restaurant.service.DeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DeliveryPersonController {

    @Autowired
    DeliveryPersonService deliveryPersonService;

    @PostMapping("/person/assignOrder/person/{pId}/order/{oId}")
    public ResponseEntity<String> assignOrder(@PathVariable("pId") Long personId, @PathVariable("oId") Long orderId){

        DeliveryPerson deliveryPerson =null;

            deliveryPerson = deliveryPersonService.fetchAndAssignOrder(personId,orderId);

        return new ResponseEntity<String>(deliveryPerson.getOrder().getStatus(),HttpStatus.OK);
    }


    @GetMapping("/person/getDeliveryStatus/{pId}")
    public ResponseEntity<Map<String,Object>> assignOrder(@PathVariable("pId") Long personId){
        Map<String,Object> deliveryDetails=null;

            deliveryDetails = deliveryPersonService.getDeliveryStatus(personId);

        return new ResponseEntity<>(deliveryDetails,HttpStatus.OK);
    }

}
