package com.devon.building.service.impl;

import com.devon.building.entity.Building;
import com.devon.building.entity.User;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.dto.StaffResponseDTO;
import com.devon.building.repository.BuildingRepository;
import com.devon.building.repository.UserRepository;
import com.devon.building.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingSearchConvertor buildingSearchConvertor;
    @Override
    public BuildingDTO findId(Long id) {

        Building building = buildingRepository.findBuildingsById(id);
        BuildingDTO buildingDTO = buildingSearchConvertor.toBuildingDTO(building);
        return buildingDTO;
    }

    @Override
    public List<BuildingResponseDTO> searchBuildings(Map<String, String> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = builderConvertor.toBuildingSearchBuilder(params, typeCode);

        List<BuildingEntity> entities = buildingRepository.searchBuildings(buildingSearchBuilder);

//		BuildingEntity buildingEntity = buildingRepository.findById(4L).get(); // tránh lỗi NullPoniterEx
        List<BuildingResponseDTO> result = new ArrayList<>();

        for (BuildingEntity entity : entities) {
            BuildingResponseDTO buildingResponseDTO = buildingConvertor.toResponseDTO(entity);
            result.add(buildingResponseDTO);



        }
        return result;

    }

    @Override
    public ResponseDTO loadStaffByBuildingId(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<User> allStaff = userRepository.findByActiveAndUserRole(true, "ROLE_" + User.ROLE_EMPLOYEE); // đang quan lí và không quản lí
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

