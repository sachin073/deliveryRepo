package com.challenge.restaurant.Restaurant.model;

import javax.persistence.*;

/**
 * Created by sachin on 4/7/19.
 */
@Entity
@Table(name = "delivery_person")
public class DeliveryPerson {
    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column
    String status;

    @Column
    Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY)
    Order order;

    DeliveryPerson(){}

    public DeliveryPerson(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
