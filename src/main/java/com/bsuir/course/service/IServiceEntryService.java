package com.bsuir.course.service;

import com.bsuir.course.model.ServiceEntry;

import java.util.List;

public interface IServiceEntryService {
    List<ServiceEntry> findAll();
    void save(ServiceEntry serviceEntry);
    ServiceEntry findById(Long id);
    void deleteById(Long id);
    void delete(ServiceEntry serviceEntry);
}
