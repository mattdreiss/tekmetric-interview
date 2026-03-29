package com.interview.dto;

import java.util.UUID;

public record CarDto(
        UUID id,
        String vin,
        int year,
        String make,
        String model,
        String trim,
        String color,
        int mileage,
        String transmission,
        String drivetrain
) {
}
