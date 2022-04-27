package com.bsuir.course.service;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.ServiceRating;

import java.util.List;

public interface IServiceRatingService {
    List<ServiceRating> findAll();
    void save(ServiceRating serviceRating);
    ServiceRating findById(String id);
    void deleteById(String id);
    void delete(ServiceRating serviceRating);
}
