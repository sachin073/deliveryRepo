package com.challenge.restaurant.Restaurant;

import com.challenge.restaurant.Restaurant.controller.DeliveryPersonController;
import com.challenge.restaurant.Restaurant.repository.DeliveryPersonRepository;
import com.challenge.restaurant.Restaurant.service.DeliveryPersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

}
