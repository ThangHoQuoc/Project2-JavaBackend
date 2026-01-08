package com.devon.building.model.response;

import com.devon.building.model.dto.AbstractDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingSearchResponse extends AbstractDTO {

    private String name;
    private String address;
    private Long numberOfBasement;
    private String managerName;
    private String managerPhone;
    private Long floorArea;
    private String rentArea;
    private String emptyArea;
    private Long rentPrice;
    private String serviceFee;
    private Double brokerageFee;
}
