package com.devon.building.service;

import com.devon.building.entity.BuildingEntity;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.model.response.BuildingSearchResponse;

import java.util.List;

public interface
BuildingService {
    BuildingDTO findId(Long id);

    ResponseDTO loadStaffByBuildingId(Long id);

    List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest);

    BuildingEntity create(BuildingDTO request);

     void delete(List<Long> ids) ;

     BuildingEntity update(BuildingDTO building);

     void assignBuilding(Long buildingId, List<Long> staffIds);

}
