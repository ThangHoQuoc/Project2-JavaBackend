package com.devon.building.repository;

import com.devon.building.entity.RentAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RentAreaRepository extends JpaRepository<RentAreaEntity,Long> {

    Long getRentAreaEntity_ValueByBuildingEntity_Id(Long id);

}