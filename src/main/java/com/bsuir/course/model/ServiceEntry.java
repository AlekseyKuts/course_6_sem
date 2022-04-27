package com.bsuir.course.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="serviceentries")
public class ServiceEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String reason;
    private String vin;
    private String brand;
    private String model;
    private int year;

    public ServiceEntry(String name, String phone, String email, String reason, String vin, String brand, String model, int year) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.reason = reason;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public ServiceEntry() {
    }
}
