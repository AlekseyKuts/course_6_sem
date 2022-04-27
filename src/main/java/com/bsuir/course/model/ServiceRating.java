package com.bsuir.course.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="servicerating")
public class ServiceRating {
    @Id
    private String idPhone;

    private int rate;

    public ServiceRating(int rate) {
        this.rate = rate;
    }

    public ServiceRating(String idPhone, int rate) {
        this.idPhone = idPhone;
        this.rate = rate;
    }

    public ServiceRating(){

    }


}