package com.bsuir.course.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="serviceentries")
public class ServiceEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reason;
    private String vin;
    private String brand;
    private String model;
    private int year;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
