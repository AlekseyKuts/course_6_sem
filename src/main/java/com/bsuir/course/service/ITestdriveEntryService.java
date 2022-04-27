package com.bsuir.course.service;

import com.bsuir.course.model.TestdriveEntry;

import java.util.List;

public interface ITestdriveEntryService {
    List<TestdriveEntry> findAll();
    void save(TestdriveEntry testdriveEntry);
    TestdriveEntry findById(Long id);
    void deleteById(Long id);
    void delete(TestdriveEntry testdriveEntry);
    List<TestdriveEntry> findByCarId(Long id);
}
