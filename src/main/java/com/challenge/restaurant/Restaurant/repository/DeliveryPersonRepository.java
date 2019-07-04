package com.challenge.restaurant.Restaurant.repository;

import com.challenge.restaurant.Restaurant.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sachin on 4/7/19.
 */
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson,Long> {

}
