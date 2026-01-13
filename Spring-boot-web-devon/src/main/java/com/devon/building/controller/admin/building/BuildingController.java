package com.devon.building.controller.admin.building;


import com.devon.building.entity.BuildingEntity;
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

       List<BuildingSearchResponse>  buildingSearchResponses = buildingService.searchBuildings(buildingSearchRequest);


       for (BuildingSearchResponse building : buildingSearchResponses ){
           resultsBuilding.add(building);
       }



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
    public String createBuilding(@PathVariable Long id, Model model){
        BuildingDTO building = buildingService.findId(id);
        model.addAttribute("districts", District.getDistrict());
        model.addAttribute("typeCode", RentType.getTypeCode());
        model.addAttribute("building", building);
        return "admin/building/buildingEdit";
    }
}
