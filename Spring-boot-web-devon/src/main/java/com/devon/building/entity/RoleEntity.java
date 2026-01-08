package com.devon.building.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user", unique = true)
    String name;

    @Column(name = "code")
    String code;

//	@OneToMany(mappedBy = "role" )
//	List<UserRoleEntity> userRoleEntities = new ArrayList<>();

    @ManyToMany
    List<UserEntity> users = new ArrayList<>();




}