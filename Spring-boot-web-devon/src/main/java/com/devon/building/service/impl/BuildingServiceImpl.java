package com.devon.building.service.impl;

import com.devon.building.convertor.BuildingConvertor;
import com.devon.building.entity.BuildingEntity;
import com.devon.building.entity.RentAreaEntity;
import com.devon.building.entity.UserEntity;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.dto.StaffResponseDTO;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.model.response.BuildingSearchResponse;
import com.devon.building.repository.BuildingRepository;
import com.devon.building.repository.RentAreaRepository;
import com.devon.building.repository.UserRepository;
import com.devon.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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




    @Override
    public BuildingSearchResponse findId(Long id) {

        BuildingEntity building = buildingRepository.findBuildingsById(id);
        BuildingSearchResponse buildingDTO = buildingConvertor.toResponseDTO(building);
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

    @Override
    public ResponseDTO loadStaffByBuildingId(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<UserEntity> allStaff = userRepository.findByActiveAndUserRole(true, "ROLE_" + UserEntity.ROLE_EMPLOYEE); // đang quan lí và không quản lí
//               Set<User> assignedBuilding = buildingRepository... // đang quản lí
        List<StaffResponseDTO> staffResponseDTO = new ArrayList<>();

        /*
        for (User user : allStaff) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setId(user.getId());
            staffResponseDTO.setUserName(user.getUserName());
            staffResponseDTO.setChecked("");
            if (assignedBuilding.contains(user.getId())) {
                staffResponseDTO.setChecked("checked");

                staffResponseDTOS.add(staffResponseDTO);


         */

        StaffResponseDTO staff1 = new StaffResponseDTO();
        staff1.setId(22L);
        staff1.setUserName("DevonStaff1");
        staff1.setChecked("checked");

        StaffResponseDTO staff2 = new StaffResponseDTO();
        staff2.setId(33L);
        staff2.setUserName("DevonStaff2");
        staff2.setChecked("");

        StaffResponseDTO staff3 = new StaffResponseDTO();
        staff3.setId(44L);
        staff3.setUserName("AndyNgo");
        staff3.setChecked("checked");
        staffResponseDTO.add(staff1);
        staffResponseDTO.add(staff2);
        staffResponseDTO.add(staff3);


        responseDTO.setData(staffResponseDTO);
        responseDTO.setMessage("Load staffs Successfully");


        return responseDTO;
    }
}

