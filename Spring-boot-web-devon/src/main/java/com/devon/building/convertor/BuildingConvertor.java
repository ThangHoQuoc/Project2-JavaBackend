package com.devon.building.convertor;

import com.devon.building.entity.BuildingEntity;
import com.devon.building.entity.RentAreaEntity;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BuildingConvertor {

    @Autowired
    private ModelMapper modelMapper;


    public BuildingDTO toBuildingDTO(BuildingEntity entity) {

        if (entity == null) return null;

        BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);

        if (entity.getRentAreaEntities() != null && !entity.getRentAreaEntities().isEmpty()) {
            String rentArea = entity.getRentAreaEntities()
                    .stream()
                    .map(r -> r.getValue().toString())
                    .collect(Collectors.joining(","));
            dto.setRentArea(rentArea);
        }

        dto.setManagerPhoneNumber(entity.getManagerphone());

        dto.setRentPrice(entity.getPrice());

        if (entity.getType() != null) {
            dto.setTypeCode(Arrays.asList(entity.getType().split(",")));
        }

        return dto;
    }


    public BuildingSearchResponse toResponseDTO(BuildingEntity entity) {

        BuildingSearchResponse dto = modelMapper.map(entity, BuildingSearchResponse.class);

        if (entity.getRentAreaEntities() != null) {
            String rentArea = entity.getRentAreaEntities()
                    .stream()
                    .map(r -> r.getValue().toString())
                    .collect(Collectors.joining(","));
            dto.setRentArea(rentArea);
        }

        StringBuilder append = new StringBuilder();
        if(entity.getStreet() != null){
            append.append(entity.getStreet());


        }
        dto.setAddress(
                Stream.of(entity.getStreet(), entity.getWard(), entity.getDistrict())
                        .filter(s -> s != null && !s.isBlank())
                        .collect(Collectors.joining(", "))
        );

        return dto;
    }


    public BuildingEntity toBuildingEntity(BuildingDTO dto) {

        BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);

        if (dto.getTypeCode() != null) {
            entity.setType(String.join(",", dto.getTypeCode()));
        }

        return entity;
    }


    public void updateEntity(BuildingDTO dto, BuildingEntity entity) {

        modelMapper.map(dto, entity);

        if (dto.getTypeCode() != null) {
            entity.setType(String.join(",", dto.getTypeCode()));
        }


    }
}
