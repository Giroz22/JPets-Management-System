package com.jpets.service;

import java.util.List;

import com.jpets.exceptions.IdNotFoundException;
import com.jpets.models.PetEntity;
import com.jpets.repository.PetRepository;
import com.jpets.service.abstract_service.IPetService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PetService implements IPetService {
    PetRepository petRepository;

    public PetService(){
        this.petRepository = new PetRepository();
    }

    @Override
    public PetEntity create(PetEntity pet) {
        PetEntity newPet = petRepository.create(pet);

        return newPet;
    }

    @Override
    public List<PetEntity> getAll() {
        return petRepository.getAll();
    }

    @Override
    public PetEntity getById(String id) throws IdNotFoundException{        
        return findPet(id);
    }

    @Override
    public PetEntity update(PetEntity entityUpdate, String id) throws IdNotFoundException{
        findPet(id);

        return petRepository.update(entityUpdate);
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
