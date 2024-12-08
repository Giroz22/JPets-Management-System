package com.jpets.service;

import com.jpets.models.StoreEntity;
import com.jpets.repository.StoreRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StoreService {

    StoreRepository storeRepository;

    public StoreEntity getStore(){
        return storeRepository.getStore();
    }
}
