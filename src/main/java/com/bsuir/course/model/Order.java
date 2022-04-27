package com.bsuir.course.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="carorder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String noteOrder;
    private String checkField;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    public Order(String name, String phone, String email, Car car, String noteOrder) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.car = car;
        this.noteOrder = noteOrder;
    }

    public Order(){

    }


}
