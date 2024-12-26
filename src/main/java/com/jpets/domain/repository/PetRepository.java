package com.jpets.domain.repository;

import com.jpets.domain.models.PetEntity;

public class PetRepository extends GenericRepository<PetEntity, String>{
    public PetRepository(){
        super(PetEntity.class);
    }
}
