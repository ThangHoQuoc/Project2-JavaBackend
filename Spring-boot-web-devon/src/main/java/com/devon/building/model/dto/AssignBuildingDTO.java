package com.devon.building.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssignBuildingDTO {
    @NotNull(message = "Building Id is required")
    private Long buildingId;

    @Size(min = 1,message = "")
    @JsonProperty("staffs")
    private List<Long> staffId;
}
