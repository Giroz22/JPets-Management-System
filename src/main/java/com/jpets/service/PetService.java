package com.jpets.service;

import java.util.List;

import com.jpets.controller.dtos.request.PetRequests;
import com.jpets.controller.dtos.response.PetResponse;
import com.jpets.exceptions.IdNotFoundException;
import com.jpets.models.PetEntity;
import com.jpets.repository.PetRepository;
import com.jpets.repository.StoreRepository;
import com.jpets.service.abstract_service.IPetService;
import com.jpets.utils.mappers.PetMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PetService implements IPetService {
    private PetRepository petRepository;
    private StoreService storeService;

    public PetService(){
        this.petRepository = new PetRepository();
        this.storeService = new StoreService(new StoreRepository());
    }

    @Override
    public PetResponse create(PetRequests request) {
        PetEntity pet = PetMapper.INSTANCE.requestToEntity(request);

        pet.setPicture("");
        pet.setStore(this.storeService.getStore());

        PetEntity newPet = petRepository.create(pet);

        return PetMapper.INSTANCE.entityToResponse(newPet);
    }

    @Override
    public List<PetResponse> getAll() {
        return PetMapper.INSTANCE.entityToResponse(petRepository.getAll());
    }

    @Override
    public PetResponse getById(String id) throws IdNotFoundException{        
        return PetMapper.INSTANCE.entityToResponse(findPet(id));
    }

    @Override
    public PetResponse update(PetRequests request, String id) throws IdNotFoundException{
        PetEntity petUpdate = PetMapper.INSTANCE.requestToEntity(request);
        
        findPet(id);

        PetEntity petUpdated = petRepository.update(petUpdate);

        return PetMapper.INSTANCE.entityToResponse(petUpdated);
    }

    @Override
    public void delete(String id) throws IdNotFoundException{
        PetEntity petDelete = findPet(id);
        this.petRepository.deleteT(petDelete);
    }

    public PetEntity findPet(String idPet){
        PetEntity petFound = this.petRepository.findById(idPet);

        if (petFound == null) throw new IdNotFoundException("Mascota", idPet);
        
        return petFound;
    }
}
