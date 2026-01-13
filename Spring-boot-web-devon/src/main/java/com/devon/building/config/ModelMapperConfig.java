package com.devon.building.config;

import com.devon.building.entity.BuildingEntity;
import com.devon.building.model.dto.BuildingDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        modelMapper.typeMap(BuildingEntity.class, BuildingDTO.class)
                .addMappings(mapper -> mapper.skip(BuildingDTO::setRentArea));
        return modelMapper;
    }



}
