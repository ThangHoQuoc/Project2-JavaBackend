package com.devon.building.api.admin;

import com.devon.building.entity.BuildingEntity;
import com.devon.building.model.dto.BuildingDTO;
import com.devon.building.model.dto.ResponseDTO;
import com.devon.building.model.request.AssignmentRequestDTO;
import com.devon.building.service.BuildingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/admin/api/buildings")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createBuilding(
            @RequestBody @Valid BuildingDTO buildingDTO,
            BindingResult bindingResult) {

        ResponseDTO responseDTO = new ResponseDTO();

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());

            responseDTO.setMessage("Failed to create building");
            responseDTO.setDetail(errors);
            return ResponseEntity.badRequest().body(responseDTO);
        }

        try {
            BuildingEntity buildingEntity = buildingService.create(buildingDTO);

            responseDTO.setMessage("Created Building Successfully");
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            responseDTO.setMessage("Create building failed");
            responseDTO.setDetail(List.of(e.getMessage()));
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }


    @PutMapping
    public ResponseEntity<ResponseDTO> updateBuilding(@RequestBody @Valid BuildingDTO buildingDTO, BindingResult bindingResult) {

        ResponseDTO responseDTO = new ResponseDTO();

        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
                responseDTO.setDetail(errorMessages);
                responseDTO.setMessage("Failed to add Building.");
                return ResponseEntity.badRequest().body(responseDTO);
            }

            if (buildingDTO.getId() == null) {
                responseDTO.setMessage("Building id is required");
                return ResponseEntity.badRequest().body(responseDTO);
            }

            BuildingEntity buildingEntity = buildingService.update(buildingDTO);

            responseDTO.setMessage("Update Building Successfully");
            return ResponseEntity.ok().body(responseDTO);

        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDTO);
        }

    }


    @DeleteMapping("/{ids}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable String ids) {

        List<Long> buildingIds = Arrays.stream(ids.split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .toList();

        buildingService.delete(buildingIds);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Deleted Building Successfully");
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/{id}/staff")
    public ResponseEntity<ResponseDTO> loadStaff(@PathVariable("id") Long id) {
        ResponseDTO responseDTO = buildingService.loadStaffByBuildingId(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/assign")
    public ResponseEntity<ResponseDTO> assignBuilding(
            @RequestBody @Valid AssignmentRequestDTO request,
            BindingResult bindingResult) {

        ResponseDTO responseDTO = new ResponseDTO();

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());

            responseDTO.setMessage("Failed to assign building");
            responseDTO.setDetail(errors);
            return ResponseEntity.badRequest().body(responseDTO);
        }

        try {
            buildingService.assignBuilding(request.getBuildingId(),request.getStaffIds());

            responseDTO.setMessage("Assign Building Successfully");
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            responseDTO.setMessage("Assign Building Failed");
            responseDTO.setDetail(List.of(e.getMessage()));
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }


}