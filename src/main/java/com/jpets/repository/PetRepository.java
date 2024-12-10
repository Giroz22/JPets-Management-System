package com.jpets.repository;

import com.jpets.models.PetEntity;

public class PetRepository extends GenericRepository<PetEntity, String>{
    public PetRepository(){
        super(PetEntity.class);
    }
}
