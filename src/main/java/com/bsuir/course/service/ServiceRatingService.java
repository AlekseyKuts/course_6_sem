package com.bsuir.course.service;

import com.bsuir.course.model.ServiceRating;
import com.bsuir.course.repository.ServiceRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRatingService implements IServiceRatingService{

    @Autowired
    private ServiceRatingRepository repository;

    @Override
    public List<ServiceRating> findAll() {
        return (List<ServiceRating>) repository.findAll();
    }

    @Override
    public void save(ServiceRating serviceRating) {
        repository.save(serviceRating);
    }

    @Override
    public ServiceRating findById(String id) {
        Optional<ServiceRating> serviceRating = repository.findById(id);
        if (serviceRating.isPresent()) return serviceRating.get();
        else return null;
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(ServiceRating serviceRating) {
        repository.delete(serviceRating);
    }

    @Override
    public ServiceRating findServiceRatingByCustomerId(Long customerId) {
        return repository.findServiceRatingByCustomerId(customerId);
    }


}
