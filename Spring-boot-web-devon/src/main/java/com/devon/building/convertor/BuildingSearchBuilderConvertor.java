package com.devon.building.convertor;

import com.devon.building.builder.BuildingSearchBuilder;
import com.devon.building.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class BuildingSearchBuilderConvertor {

    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest request) {

        return new BuildingSearchBuilder.Builder()
                .setName(request.getName())
                .setFloorArea(request.getFloorArea())
                .setWard(request.getWard())
                .setStreet(request.getStreet())
                .setDistrictId(request.getDistrict())
                .setNumberOfBasement(request.getNumberOfBasement())
                .setManagerName(request.getManagerNane())
                .setManagerPhoneNumber(request.getManagerPhoneNumber())
                .setTypeCode(request.getTypeCode())
                .setRentAreaFrom(request.getRentAreaFrom())
                .setRentAreaTo(request.getRentAreaTo())
                .setRentPriceFrom(request.getRentPriceFrom())
                .setRentPriceTo(request.getRentPriceTo())
                .setLevel(request.getLevel())
                .setDirection(request.getDirection())
                .setStaffId(request.getStaffId())
                .build();
    }
}
