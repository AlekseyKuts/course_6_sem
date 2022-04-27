package com.bsuir.course.service;

import com.bsuir.course.model.Feedback;
import com.bsuir.course.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService{

    @Autowired
    private FeedbackRepository repository;

    @Override
    public List<Feedback> findAll() {
        return (List<Feedback>) repository.findAll();
    }

    @Override
    public void save(Feedback feedback) {
        repository.save(feedback);
    }

    @Override
    public Feedback findById(Long id) {
        Optional<Feedback> feedback = repository.findById(id);
        if (feedback.isPresent()) return feedback.get();
        else return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Feedback feedback) {
        repository.delete(feedback);
    }

    @Override
    public List<Feedback> findByCarId(Long id) {
        return repository.findByCarId(id);
    }
}
