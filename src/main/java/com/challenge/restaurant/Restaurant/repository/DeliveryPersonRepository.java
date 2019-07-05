package com.challenge.restaurant.Restaurant.repository;

import com.challenge.restaurant.Restaurant.model.DeliveryPerson;
import com.challenge.restaurant.Restaurant.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sachin on 4/7/19.
 */
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson,Long> {


    List<DeliveryPerson> findAllByActive(Boolean active);

    DeliveryPerson findDeliveryPersonById(Long id);
}
