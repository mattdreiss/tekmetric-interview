package com.interview.service;

import com.interview.dto.CarDto;
import com.interview.exception.CarNotFoundException;
import com.interview.exception.CarUpdateIdMismatchException;
import com.interview.mapper.CarMapper;
import com.interview.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarService {

    private static final String CAR_NOT_FOUND_MSG = "Car not found with ID %s";
    private static final String CAR_ID_MISMATCH_MSG = "Cannot update car with ID %s due to ID mismatch in the update object";

    private final CarMapper carMapper;
    private final CarRepository carRepository;

    public List<CarDto> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDto)
                .toList();
    }

    public CarDto getCar(final UUID id) {
        return carRepository.findById(id)
                .map(carMapper::toDto)
                .orElseThrow(() -> new CarNotFoundException(String.format(CAR_NOT_FOUND_MSG, id)));
    }

    @Transactional
    public CarDto createCar(final CarDto carDto) {
        final var added = carRepository.save(carMapper.toEntity(carDto));
        return carMapper.toDto(added);
    }

    @Transactional
    public CarDto updateCar(final UUID id, final CarDto carDto) {
        if (carDto.id() != null && !id.equals(carDto.id())) {
            throw new CarUpdateIdMismatchException(String.format(CAR_ID_MISMATCH_MSG, id));
        }

        final var existingCar = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(String.format(CAR_NOT_FOUND_MSG, id)));
        carMapper.updateEntityFromDto(carDto, existingCar);

        final var updated = carRepository.save(existingCar);
        return carMapper.toDto(updated);
    }

    @Transactional
    public void deleteCar(final UUID id) {
        carRepository.deleteById(id);
    }
}
