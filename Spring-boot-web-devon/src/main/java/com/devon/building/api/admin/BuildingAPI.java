package com.devon.building.api.admin;

import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/admin/api/buildings")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createBuilding(@RequestBody @Valid BuildingDTO buildingDTO, BindingResult bindingResult) {

        ResponseDTO responseDTO = new ResponseDTO();

        try{
            if(bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
                responseDTO.setDetail(errorMessages);
                responseDTO.setMessage("Failed to add Building.");
                return ResponseEntity.badRequest().body(responseDTO);
            }



            responseDTO.setMessage("Created Building Successfully");
            return ResponseEntity.ok().body(responseDTO);

        }catch(Exception e){
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDTO);
        }

    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateBuilding(@RequestBody @Valid BuildingDTO buildingDTO, BindingResult bindingResult) {

        ResponseDTO responseDTO = new ResponseDTO();

        try{
            if(bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
                responseDTO.setDetail(errorMessages);
                responseDTO.setMessage("Failed to add Building.");
                return ResponseEntity.badRequest().body(responseDTO);
                     }

            if(buildingDTO.getId() == null) {
                responseDTO.setMessage("Building id is required");
                return ResponseEntity.badRequest().body(responseDTO);
            }
            responseDTO.setMessage("Update Building Successfully");
            return ResponseEntity.ok().body(responseDTO);

        }catch(Exception e){
                responseDTO.setMessage(e.getMessage());
                return ResponseEntity.internalServerError().body(responseDTO);
            }

    }


    @DeleteMapping("/{ids}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("ids") String ids) {
        System.out.println("Deleting " + ids);
// Continue.
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Deleted Building Successfully");
        return ResponseEntity.ok().body(responseDTO);


    }


    @GetMapping("/{id}/staff")
    public ResponseEntity<ResponseDTO> loadStaff(@PathVariable("id") Long id) {
            ResponseDTO responseDTO = buildingService.loadStaffByBuildingId(id);
            return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/assign")
    public ResponseEntity<ResponseDTO> assignBuilding(@RequestBody @Valid BuildingDTO buildingDTO,BindingResult bindingResult) {

        ResponseDTO responseDTO = new ResponseDTO();
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
                responseDTO.setDetail(errorMessages);
                responseDTO.setMessage("Failed to assign Building.");
            }

            //service
            responseDTO.setMessage("Assign Building Successfully");
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDTO);

        }
    }
}