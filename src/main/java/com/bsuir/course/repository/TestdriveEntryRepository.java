package com.bsuir.course.repository;

import com.bsuir.course.model.TestdriveEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestdriveEntryRepository extends JpaRepository<TestdriveEntry, Long> {
    List<TestdriveEntry> findByCarId(Long id);
}
