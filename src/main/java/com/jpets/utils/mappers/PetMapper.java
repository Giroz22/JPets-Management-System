package com.jpets.utils.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jpets.controller.dtos.request.PetRequests;
import com.jpets.controller.dtos.response.PetResponse;
import com.jpets.models.PetEntity;

@Mapper(componentModel = "default")
public interface PetMapper {

    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    PetResponse entityToResponse(PetEntity entity);
    List<PetResponse> entityToResponse(List<PetEntity> entities);
    PetEntity requestToEntity(PetRequests dto);
}
