package com.interview.service;

import com.interview.dto.CarDto;
import com.interview.mapper.CarMapper;
import com.interview.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private final CarMapper carMapper;
    private final CarRepository carRepository;

    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto)
                .toList();
    }

    @Transactional
    public CarDto addCar(final CarDto carDto) {
        final var added = carRepository.save(carMapper.toEntity(carDto));
        return carMapper.toDto(added);
    }
}
