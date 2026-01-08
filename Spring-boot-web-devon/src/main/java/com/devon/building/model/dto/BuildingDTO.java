package com.devon.building.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BuildingDTO extends AbstractDTO{
    @NotBlank(message = "Name Building not be blank")
    private String name;
    private String street;
    private String ward;
    @NotBlank(message = "District not be blank")
    private String district;
    private Long numberOfBasement;
    private Long floorArea;
    private String level;
    @Size(min = 1, message = "Type Code must not be less than 1")
    private List<String> typeCode;
    private String overtimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private String carFee;
    private String motofee;
    private String waterFee;
    private String structure;
    private String direction;
    private String note;
    @NotBlank(message = "Rent Area not be blank")
    private String rentArea;
    private String managerName;
    @NotBlank
    @Size(min = 10,message = "Manager Phone must not be less than 10 digit")
    private String managerPhoneNumber;

    private Long rentPrice;
    private String serviceFee;
    private double brokeragefee;


    private String image;
    private String imageBase64;
    private String imageName;

}