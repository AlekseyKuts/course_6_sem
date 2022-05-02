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
@Table(name="servicerating")
public class ServiceRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int rate;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}