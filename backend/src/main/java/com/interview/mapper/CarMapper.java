package com.interview.mapper;

import com.interview.dto.CarDto;
import com.interview.entity.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toDto(CarEntity entity);

    CarEntity toEntity(CarDto dto);

}
