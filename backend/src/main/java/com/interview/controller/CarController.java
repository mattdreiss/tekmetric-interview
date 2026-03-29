package com.interview.controller;

import com.interview.dto.CarDto;
import com.interview.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/api/car")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/api/car/{id}")
    public CarDto getCar(@PathVariable("id") final UUID id) {
        return carService.getCar(id);
    }

    @PostMapping("/api/car")
    public CarDto createCar(@RequestBody final CarDto carDto) {
        return carService.createCar(carDto);
    }

    @PutMapping("/api/car/{id}")
    public CarDto updateCar(@PathVariable("id") final UUID id, @RequestBody final CarDto carDto) {
        return carService.updateCar(id, carDto);
    }

    @DeleteMapping("/api/car/{id}")
    public void deleteCar(@PathVariable("id") final UUID id) {
        carService.deleteCar(id);
    }
}
