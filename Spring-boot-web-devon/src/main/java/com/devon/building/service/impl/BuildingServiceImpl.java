package com.devon.building.service.impl;

import com.devon.building.convertor.BuildingConvertor;
import com.devon.building.entity.AssignmentBuildingEntity;
import com.devon.building.entity.BuildingEntity;
import com.devon.building.entity.RentAreaEntity;
import com.devon.building.entity.UserEntity;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.dto.StaffResponseDTO;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.model.response.BuildingSearchResponse;
import com.devon.building.repository.AssignmentBuildingRepository;
import com.devon.building.repository.BuildingRepository;
import com.devon.building.repository.RentAreaRepository;
import com.devon.building.repository.UserRepository;
import com.devon.building.service.BuildingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConvertor buildingConvertor;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;


    @Override
    public BuildingDTO findId(Long id) {

        BuildingEntity building = buildingRepository.findBuildingsById(id);
        BuildingDTO buildingDTO = buildingConvertor.toBuildingDTO(building);
        return buildingDTO;
    }

    @Override
    public List<BuildingSearchResponse> searchBuildings(BuildingSearchRequest buildingSearchRequest) {

        List<BuildingEntity> entities = buildingRepository.searchBuildings(buildingSearchRequest);


        List<BuildingSearchResponse> result = new ArrayList<>();

        for (BuildingEntity entity : entities) {
            BuildingSearchResponse buildingResponseDTO = buildingConvertor.toResponseDTO(entity);
            buildingResponseDTO.setEmptyRentArea(null);
            result.add(buildingResponseDTO);


        }
        return result;

    }

    @Transactional
    @Override
    public BuildingEntity create(BuildingDTO request) {

        BuildingEntity building = buildingConvertor.toBuildingEntity(request);
        buildingRepository.save(building);

        if (request.getRentArea() != null && !request.getRentArea().isBlank()) {

            List<RentAreaEntity> rentAreas = Arrays.stream(request.getRentArea().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::valueOf)
                    .map(value -> {
                        RentAreaEntity area = new RentAreaEntity();
                        area.setValue(value);
                        area.setBuildingEntity(building);
                        return area;
                    })
                    .toList();

            rentAreaRepository.saveAll(rentAreas);
        }

        return building;
    }




    @Override
    @Transactional
    public void delete(List<Long> ids) {

        if (ids == null || ids.isEmpty()) {
            return;
        }

        assignmentBuildingRepository.deleteAllByBuilding_IdIn(ids);
        rentAreaRepository.deleteAllByBuildingEntity_IdIn(ids);

        buildingRepository.deleteAllByIdIn(ids);
    }



    @Override
    public ResponseDTO loadStaffByBuildingId(Long buildingId) {

        ResponseDTO responseDTO = new ResponseDTO();

        List<UserEntity> staffs = userRepository.findByActiveAndUserRole(true, "ROLE_STAFF");

        List<AssignmentBuildingEntity> assigned = assignmentBuildingRepository.findByBuilding_Id(buildingId);

        Set<Long> assignedStaffIds = assigned.stream().map(a -> a.getStaff().getId()).collect(Collectors.toSet());

        List<StaffResponseDTO> result = new ArrayList<>();

        for (UserEntity staff : staffs) {
            StaffResponseDTO dto = new StaffResponseDTO();
            dto.setId(staff.getId());
            dto.setUserName(staff.getUserName());
            dto.setChecked(assignedStaffIds.contains(staff.getId()) ? "checked" : ""
            );
            result.add(dto);
        }

        responseDTO.setData(result);
        responseDTO.setMessage("Load staffs successfully");
        return responseDTO;
    }

    @Transactional
    @Override
    public void assignBuilding(Long buildingId, List<Long> staffIds) {

        BuildingEntity building = buildingRepository.findById(buildingId).orElseThrow(() -> new RuntimeException("Building not found"));

        assignmentBuildingRepository.deleteByBuilding_Id(buildingId);

        for (Long staffId : staffIds) {
            UserEntity staff = userRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found"));

            AssignmentBuildingEntity assign = new AssignmentBuildingEntity();
            assign.setBuilding(building);
            assign.setStaff(staff);

            assignmentBuildingRepository.save(assign);
        }
    }




    @Transactional
    @Override
    public BuildingEntity update(BuildingDTO dto) {

        BuildingEntity buildingEntity = buildingRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Building not found"));

        buildingConvertor.updateEntity(dto, buildingEntity);

        rentAreaRepository.deleteAllByBuildingEntity_Id(buildingEntity.getId());

        if (dto.getRentArea() != null && !dto.getRentArea().isBlank()) {

            List<RentAreaEntity> rentAreas = Arrays.stream(dto.getRentArea().split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::valueOf)
                    .map(value -> {
                        RentAreaEntity area = new RentAreaEntity();
                        area.setValue(value);
                        area.setBuildingEntity(buildingEntity);
                        return area;
                    })
                    .toList();

            rentAreaRepository.saveAll(rentAreas);
        }

        return buildingEntity;
    }


}

