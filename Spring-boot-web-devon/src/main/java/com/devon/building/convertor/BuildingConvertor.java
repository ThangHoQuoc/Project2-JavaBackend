package com.devon.building.convertor;

import com.devon.building.entity.Building;
import com.devon.building.model.response.BuildingSearchResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingConvertor {


    @Autowired
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public BuildingSearchResponse toResponseDTO(Building entity) {
        BuildingSearchResponse dto = modelMapper.map(entity, BuildingSearchResponse.class);

        Dictrict dictrict = entity.getDictrictEntity();

        dto.setAddress(entity.getStreet() + ", " + entity.getWard() + ", " + dictrict.getName());

        List<RentAreaEntity> rentAreas = entity.getRentAreaEntities();

        dto.setRentArea(
                rentAreas.stream().map(rentArea -> rentArea.getValue().toString()).collect(Collectors.joining(",")));


        return dto;
    }

    public BuildingEntity toBuildingEntity(BuildingRequestDTO buildingRequestDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingRequestDTO, BuildingEntity.class);
        DictrictEntity district = entityManager.find( DictrictEntity.class,buildingRequestDTO.getDistrictId());
        if(district != null) {
            buildingEntity.setDictrictEntity(district);
        }else {
            throw new InvalidBuildingException("not found district by id");
        }




        return buildingEntity;

    }

}