package com.devon.building.convertor;

import com.devon.building.entity.BuildingEntity;
import com.devon.building.exception.InvalidBuildingException;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.model.response.BuildingSearchResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConvertor {


    @Autowired
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public BuildingSearchResponse toResponseDTO(BuildingEntity entity) {
        BuildingSearchResponse dto = modelMapper.map(entity, BuildingSearchResponse.class);



        return dto;
    }

    public BuildingEntity toBuildingEntity(BuildingSearchRequest buildingRequestDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingRequestDTO, BuildingEntity.class);




        return buildingEntity;

    }

}