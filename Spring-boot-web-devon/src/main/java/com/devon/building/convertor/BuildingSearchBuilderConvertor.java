package com.devon.building.convertor;

import com.devon.building.builder.BuildingSearchBuilder;
import com.devon.building.utils.MapUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildingSearchBuilderConvertor {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String, String> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtil.getOject(params, "name", String.class))
                .setFloorArea(MapUtil.getOject(params, "floorArea", Long.class))
                .setWard(MapUtil.getOject(params, "ward", String.class))
                .setStreet(MapUtil.getOject(params, "street", String.class))
                .setDistrictId(MapUtil.getOject(params, "dictrictId", Long.class))
                .setNumberOfBasement(MapUtil.getOject(params, "numberOfBasement", Long.class))
                .setManagerName(MapUtil.getOject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtil.getOject(params, "managerPhoneNumber", String.class))
                .setTypeCode(typeCode)
                .setRentAreaFrom(MapUtil.getOject(params, "rentAreaFrom", Long.class))
                .setRentAreaTo(MapUtil.getOject(params, "rentAreaTo", Long.class))
                .setRentPriceFrom(MapUtil.getOject(params, "rentPriceFrom", Long.class))
                .setRentPriceTo(MapUtil.getOject(params, "rentPriceTo", Long.class))
                .setLevel(MapUtil.getOject(params, "level", Long.class))
                .setDirection(MapUtil.getOject(params, "direction", String.class))
                .setStaffId(MapUtil.getOject(params, "staffId", Long.class))
                .build();



        return buildingSearchBuilder;
    }

}
