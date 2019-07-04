package com.challenge.restaurant.Restaurant.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by sachin on 4/7/19.
 */

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String itemName;

    @Column
    Integer cost;

    @Column
    String status;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "order")
    DeliveryPerson person;

    Order(){}

    public Order(String itemName) {
        this.itemName = itemName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeliveryPerson getPerson() {
        return person;
    }

    public void setPerson(DeliveryPerson person) {
        this.person = person;
    }
}
