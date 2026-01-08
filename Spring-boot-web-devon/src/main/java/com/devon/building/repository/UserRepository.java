package com.devon.building.repository;

import com.devon.building.entity.UserEntity;
import com.devon.building.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);

    void deleteByIdIn(List<Long> ids);

    List<UserEntity> findByActiveAndUserRole(boolean active, String userRole);
}
