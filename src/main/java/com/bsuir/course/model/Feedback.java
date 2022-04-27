package com.bsuir.course.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(name = "feedback")
    private String feedbackField;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Feedback(String name, String feedback, Car car) {
        this.name = name;
        this.feedbackField = feedback;
        this.car = car;
    }

    public Feedback() {

    }
}
