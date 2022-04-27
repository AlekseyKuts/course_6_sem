package com.bsuir.course.service;

import com.bsuir.course.model.Feedback;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> findAll();
    void save(Feedback feedback);
    Feedback findById(Long id);
    void deleteById(Long id);
    void delete(Feedback feedback);
    List<Feedback> findByCarId(Long id);
}
