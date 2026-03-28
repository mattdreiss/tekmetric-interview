package com.interview.dto;

import java.util.UUID;

public record CarDto(
        UUID id,
        String year,
        String make,
        String model,
        String trim,
        String color,
        String vin,
        String transmission,
        String drivetrain
) {
}
