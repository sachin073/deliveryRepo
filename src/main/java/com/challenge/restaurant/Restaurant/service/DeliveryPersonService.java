package com.challenge.restaurant.Restaurant.service;

import com.challenge.restaurant.Restaurant.exception.IllegalOrderException;
import com.challenge.restaurant.Restaurant.model.DeliveryPerson;
import com.challenge.restaurant.Restaurant.model.Order;
import com.challenge.restaurant.Restaurant.model.OrderStatus;
import com.challenge.restaurant.Restaurant.repository.DeliveryPersonRepository;
import com.challenge.restaurant.Restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DeliveryPersonService {

    @Autowired
    DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    OrderRepository orderRepository;


   public List<DeliveryPerson> getAllActive(){

     return  deliveryPersonRepository.findAllByActive(true);
    }

    public DeliveryPerson fetchAndAssignOrder(Long personId,Long orderId){
        Order order= orderRepository.findOrderById(orderId);
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findDeliveryPersonById(personId);

        if (order==null || deliveryPerson==null){
            throw new IllegalOrderException("order assignment fail. Invalid person or order");
        }
        return assignOrderToPerson(deliveryPerson,order);
    }



    private DeliveryPerson assignOrderToPerson(DeliveryPerson person,Order order){
       person.setActive(true);
       order.setStatus(OrderStatus.READY_TO_DELIVER.getText());
       person.setOrder(order);
       deliveryPersonRepository.save(person);
       return person;
    }




    public Map<String,Object> getDeliveryStatus(Long personId){
       DeliveryPerson person= deliveryPersonRepository.findDeliveryPersonById(personId);
        Map<String,Object> deliveryDetailsMap=new HashMap<>();
        if (person==null){
            throw new IllegalOrderException("unknown person");
        }

        deliveryDetailsMap.put("Delivery person name",person.getName());
        if (person.getActive()){
            deliveryDetailsMap.put("Scheduled for delivery","Yes");
            deliveryDetailsMap.put("Order Details",person.getOrder());
            deliveryDetailsMap.put("Time Left to deliver",(person.getOrder().getOrderPlacedTime().getTime()-System.currentTimeMillis())/ (60 * 1000) % 60 +" mins");
        }else {
            deliveryDetailsMap.put("Scheduled for delivery","Yes");

        }
        deliveryDetailsMap.put("Delivery person name",person.getName());

        return deliveryDetailsMap;
    }



}
