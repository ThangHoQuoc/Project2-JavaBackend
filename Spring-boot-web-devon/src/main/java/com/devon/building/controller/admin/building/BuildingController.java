package com.devon.building.controller.admin.building;

import com.devon.building.entity.Building;
import com.devon.building.enums.District;
import com.devon.building.enums.RentType;
import com.devon.building.form.CustomerForm;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.request.BuildingSearchRequest;
import com.devon.building.model.response.BuildingSearchResponse;
import com.devon.building.service.BuildingService;
import com.devon.building.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/buildings")
public class BuildingController {


    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;
    @GetMapping("/list")
    public String getList(@ModelAttribute BuildingSearchRequest buildingSearchRequest, Model model) {
        model.addAttribute("modelSearch", buildingSearchRequest);
        model.addAttribute("districts", District.getDistrict());
        model.addAttribute("staffs",userService.getAllStaff());
        model.addAttribute("typeCode", RentType.getTypeCode());
        List<BuildingSearchResponse> resultsBuilding = new ArrayList<>();

        BuildingSearchResponse buildingSearchResponse = buildingService.


        BuildingSearchResponse building1 = new BuildingSearchResponse();
        building1.setId(22L);
        building1.setName("Devon Building");
        building1.setAddress("Man Thiện, Phường ó, Quận Thủ Đức");
        building1. setManagerName("Anh Manh");
        building1. setManagerPhone("090-0001-068");

        BuildingSearchResponse building2 = new BuildingSearchResponse();
        building2.setId(33L);
        building2.setName("lankMark Building");
        building2.setAddress("Man Thiện, Phường ó, Quận Thủ Đức");
        building2. setManagerName("Chị Hải");
        building2. setManagerPhone("090-0001-068");

        BuildingSearchResponse building3 = new BuildingSearchResponse();
        building3.setId(11L);
        building3.setName("Top Building");
        building3.setAddress("Man Thiện, Phường ó, Quận Thủ Đức");
        building3. setManagerName("Anh Manh");
        building3. setManagerPhone("090-0001-068");

        resultsBuilding.add(building3);
        resultsBuilding.add(building1);
        resultsBuilding.add(building2);

        model.addAttribute("resultsBuilding",resultsBuilding);
        return "admin/building/buildingList";
    }

    @GetMapping("/edit")
    public String createBuilding(Model model) {
        model.addAttribute("districts", District.getDistrict());
        model.addAttribute("typeCode", RentType.getTypeCode());
        model.addAttribute("building",new BuildingDTO());
        return "admin/building/buildingEdit";
    }
    @GetMapping("/update/{id}")
    public String createBuilding(@PathVariable Long id, Model model) {
        BuildingDTO building =  buildingService.findId(id);
        model.addAttribute("districts", District.getDistrict());
        model.addAttribute("typeCode", RentType.getTypeCode());
        model.addAttribute("building",building);
        return "admin/building/buildingEdit";
    }
}
