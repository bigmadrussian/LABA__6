package com.example.testthymeleafwebapp.example.dao;

import com.example.testthymeleafwebapp.example.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
