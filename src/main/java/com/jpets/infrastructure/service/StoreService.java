package com.jpets.infrastructure.service;

import com.jpets.domain.models.StoreEntity;
import com.jpets.domain.repository.StoreRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StoreService {

    StoreRepository storeRepository;

    public StoreEntity getStore(){
        return storeRepository.getStore();
    }
}
