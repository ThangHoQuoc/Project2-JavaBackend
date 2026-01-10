package com.devon.building.repository;

import com.devon.building.builder.BuildingSearchBuilder;
import com.devon.building.entity.BuildingEntity;
import com.devon.building.model.request.BuildingSearchRequest;

import java.util.List;

public interface BuildingRepositoryCustom {

    List<BuildingEntity> searchBuildings(BuildingSearchRequest buildingSearchRequest);

}
