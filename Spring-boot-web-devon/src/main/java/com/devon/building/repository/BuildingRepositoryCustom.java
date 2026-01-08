package com.devon.building.repository;

import com.devon.building.builder.BuildingSearchBuilder;
import com.devon.building.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {

    List<BuildingEntity> searchBuildings(BuildingSearchBuilder buildingSearchBuilder);

}
