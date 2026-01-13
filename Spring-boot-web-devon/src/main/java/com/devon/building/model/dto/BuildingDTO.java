package com.devon.building.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO {

    @NotBlank(message = "Building name must not be blank")
    private String name;

    @NotBlank(message = "Street must not be blank")
    private String street;

    @NotBlank(message = "Ward must not be blank")
    private String ward;

    @NotBlank(message = "District must not be blank")
    private String district;

    @Min(value = 0, message = "Number of basement must be greater than or equal to 0")
    private Long numberOfBasement;

    @Positive(message = "Floor area must be greater than 0")
    private Long floorArea;

    private String level;

    @NotEmpty(message = "Type Code must not be empty")
    private List<String> typeCode;

    private Long overtimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private Long carFee;
    private Long motoFee;
    private Long waterFee;
    private String structure;
    private String direction;
    private String note;

    @NotBlank(message = "Rent area must not be blank")
    @Pattern(
            regexp = "^\\d+(,\\d+)*$",
            message = "Rent area must be numbers separated by commas (e.g. 100,200)"
    )
    private String rentArea;

    @NotBlank(message = "Manager name must not be blank")
    private String managerName;

    @NotBlank(message = "Manager phone must not be blank")
    @Pattern(
            regexp = "^\\d{10}$",
            message = "Manager phone number must contain exactly 10 digits"
    )
    private String managerPhoneNumber;

    @Positive(message = "Rent price must be greater than 0")
    private Double rentPrice;

    private Long serviceFee;

    @PositiveOrZero(message = "Brokerage fee must be greater than or equal to 0")
    private Double brokerageFee;

    private String image;
    private String imageBase64;
    private String imageName;
}
