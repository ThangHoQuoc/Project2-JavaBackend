package com.devon.building.service;

import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.model.response.BuildingSearchResponse;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    BuildingSearchResponse findId(Long id);

    ResponseDTO loadStaffByBuildingId(Long id);

    List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest);
}
