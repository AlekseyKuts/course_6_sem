package com.bsuir.course.service;

import com.bsuir.course.model.TestdriveEntry;
import com.bsuir.course.repository.TestdriveEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TestdriveEntryService implements ITestdriveEntryService{

    @Autowired
    private TestdriveEntryRepository repository;

    @Override
    public List<TestdriveEntry> findAll() {
        return (List<TestdriveEntry>) repository.findAll();
    }

    @Override
    public void save(TestdriveEntry testdriveEntry) {
        repository.save(testdriveEntry);
    }

    @Override
    public TestdriveEntry findById(Long id) {
        Optional<TestdriveEntry> testdriveEntry = repository.findById(id);
        if (testdriveEntry.isPresent()) return testdriveEntry.get();
        else return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(TestdriveEntry testdriveEntry) {
        repository.delete(testdriveEntry);
    }

    @Override
    public List<TestdriveEntry> findByCarId(Long id) {
        return repository.findByCarId(id);
    }
}
