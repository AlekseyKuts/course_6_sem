package com.bsuir.course.repository;

import com.bsuir.course.model.ServiceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceEntryRepository extends JpaRepository<ServiceEntry, Long> {

}
