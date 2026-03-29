package com.interview.controller;

import com.interview.exception.CarNotFoundException;
import com.interview.exception.CarUpdateIdMismatchException;
import com.interview.service.CarService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
@ExtendWith(SpringExtension.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarService carService;

    @Nested
    class HandleCarNotFoundException {

        @Test
        void returns404() throws Exception {
            when(carService.getCar(any())).thenThrow(new CarNotFoundException("not found"));

            mockMvc.perform(get("/api/car/{id}", UUID.randomUUID()))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class HandleCarUpdateIdMismatchException {

        @Test
        void returns400() throws Exception {
            final var id1 = UUID.randomUUID();
            final var id2 = UUID.randomUUID();

            when(carService.updateCar(any(), any())).thenThrow(new CarUpdateIdMismatchException("id mismatch"));

            mockMvc.perform(put("/api/car/{id}", id1)
                            .contentType("application/json")
                            .content("{\"id\": \"" + id2 + "\"}"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class HandleUnexpectedException {

        @Test
        void returns500() throws Exception {
            when(carService.getAllCars()).thenThrow(new RuntimeException("unexpected"));

            mockMvc.perform(get("/api/car"))
                    .andExpect(status().isInternalServerError());
        }
    }
}
