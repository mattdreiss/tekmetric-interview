package com.interview.controller;

import com.interview.dto.CarDto;
import com.interview.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/api/car")
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/api/car")
    public CarDto addCar(@RequestBody final CarDto carDto) {
        return carService.addCar(carDto);
    }
}
