package com.bsuir.course.service;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.ServiceEntry;
import com.bsuir.course.repository.ServiceEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ServiceEntryService implements IServiceEntryService{

    @Autowired
    private ServiceEntryRepository repository;

    @Override
    public List<ServiceEntry> findAll() {
        return (List<ServiceEntry>) repository.findAll();
    }

    @Override
    public void save(ServiceEntry serviceEntry) {
        repository.save(serviceEntry);
    }

    @Override
    public ServiceEntry findById(Long id) {
        Optional<ServiceEntry> serviceEntry = repository.findById(id);
        if (serviceEntry.isPresent()) return serviceEntry.get();
        else return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(ServiceEntry serviceEntry) {
        repository.delete(serviceEntry);
    }


}
