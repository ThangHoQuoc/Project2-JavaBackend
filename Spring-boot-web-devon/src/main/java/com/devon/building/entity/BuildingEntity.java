package com.devon.building.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "building")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingEntity implements Serializable {

    @Serial
    static final long serialVersionUID = -1000119078147252957L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", length = 255, nullable = false)
    String name;

    @Column(name = "street", length = 255, nullable = false)
    String street;

    @Column(name = "ward", length = 255, nullable = false)
    String ward;

    @Column(name = "district", length = 255, nullable = false)
    String district;


    @Column(name = "rentprice", nullable = false)
    Double price;

    @Column(name = "floorarea", nullable = false)
    Long floorarea;

    @Column(name = "servicefee")
    Double servicefee;

    @Column(name = "brokeragefee")
    Double brokeragefee;

    @Column(name = "type")
    String type;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    byte[] image;

    @Column(name = "numberofbasement", nullable = false)
    Long numberofbasement;

    @Column(name = "managername", length = 255, nullable = false)
    String managername;

    @Column(name = "managerphone", length = 255, nullable = false)
    String managerphone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createddate", nullable = false)
    Date createDate;


   @OneToMany(mappedBy = "buildingEntity",fetch = FetchType.EAGER)
   List<RentAreaEntity> rentAreaEntities = new ArrayList<>();

    @OneToMany(mappedBy = "building")
    private List<AssignmentBuildingEntity> assignmentBuildings = new ArrayList<>();


}