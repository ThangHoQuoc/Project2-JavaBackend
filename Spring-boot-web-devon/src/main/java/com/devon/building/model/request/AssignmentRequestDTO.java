package com.devon.building.model.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentRequestDTO {

    @NotNull(message = "BuildingId is required")
    Long buildingId;

    @NotEmpty(message = "Staff list must not be empty")
    List<Long> staffIds;
}
