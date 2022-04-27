package com.bsuir.course.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="testdriveentries")
public class TestdriveEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String checkField;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public TestdriveEntry(String name, String phone, String email, Car car) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.car = car;
    }

    public TestdriveEntry(){

    }


}
