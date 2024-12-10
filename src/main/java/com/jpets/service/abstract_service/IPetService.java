package com.jpets.service.abstract_service;

import com.jpets.models.PetEntity;
import com.jpets.service.abstract_service.CRUD.ICreate;
import com.jpets.service.abstract_service.CRUD.IDelete;
import com.jpets.service.abstract_service.CRUD.IGetAll;
import com.jpets.service.abstract_service.CRUD.IGetById;
import com.jpets.service.abstract_service.CRUD.IUpdate;

public interface IPetService extends
    ICreate<PetEntity>,
    IGetAll<PetEntity>,
    IGetById<PetEntity, String>,
    IUpdate<PetEntity, String>,
    IDelete<String>
{

}
