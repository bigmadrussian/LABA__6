package com.example.testthymeleafwebapp.example.controller;

import com.example.testthymeleafwebapp.example.dao.CarRepository;
import com.example.testthymeleafwebapp.example.entity.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping({"/list", "/"})
    public ModelAndView getAllCars() {
        log.info("list -> connection");
        ModelAndView mav = new ModelAndView("list-cars");
        mav.addObject("cars", carRepository.findAll());
        return mav;
    }

    @GetMapping("/addCarForm")
    public ModelAndView getCarsForm() {
        ModelAndView mav = new ModelAndView("add-car-form");
        Car car = new Car();
        mav.addObject("car", car);
        return mav;
    }

    @PostMapping("/saveCar")
    public String saveCar(@ModelAttribute Car car) {
        carRepository.save(car);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long carId) {
        ModelAndView mav = new ModelAndView("add-car-form");
        Optional<Car> optionalCar = carRepository.findById(carId);
        Car car = new Car();
        if (optionalCar.isPresent()) {
            car = optionalCar.get();
        }
        mav.addObject("car", car);
        return mav;
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam Long carId) {
        carRepository.deleteById(carId);
        return "redirect:/list";
    }
}
