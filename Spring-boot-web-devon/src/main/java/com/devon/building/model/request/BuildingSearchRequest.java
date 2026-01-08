package com.devon.building.model.request;


import jakarta.annotation.Nonnull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingSearchRequest {
    //level=2&rentAreaFrom=400&rentAreaTo=500&rentPriceF
    String name;
    Long floorArea;
    String district;
    String ward;
    String street;
    String numberOfBasement;
    String direction;
    Long Level;
    Long rentAreaFrom;
    Long rentAreaTo;
    Long rentPriceFrom;
    Long rentPriceTo;
    String managerNane;
    String managerPhoneNumber;
    Long staffId;
    List<String> typeCode;


}
