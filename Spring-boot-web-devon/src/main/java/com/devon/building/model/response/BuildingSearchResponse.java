package com.devon.building.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingSearchResponse {

    Long id;
    String name;
    Long numberOfBasement;
    String address;
    Long floorArea;
    String rentArea;
    List<Long> emptyRentArea;
    long serviceFee;
    double brokerageFee;
    Long price;
    String managerName;
    String managerPhone;


}
