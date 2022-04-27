package com.bsuir.course.repository;

import com.bsuir.course.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("DELETE FROM Car c WHERE c.id = :id")
    void removeById(long id);

    Optional<List<Car>> findByEngineContaining(String engine);

}
