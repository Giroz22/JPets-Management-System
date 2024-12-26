package com.jpets.infrastructure.abstract_service;

import com.jpets.infrastructure.abstract_service.CRUD.ICreate;
import com.jpets.infrastructure.abstract_service.CRUD.IDelete;
import com.jpets.infrastructure.abstract_service.CRUD.IGetAll;
import com.jpets.infrastructure.abstract_service.CRUD.IGetById;
import com.jpets.infrastructure.abstract_service.CRUD.IUpdate;

public interface IDataService<RQ,RS,ID> extends
    ICreate<RQ,RS>,
    IGetAll<RS>,
    IGetById<RS, ID>,
    IUpdate<RQ, RS, ID>,
    IDelete<ID>
{
    
}
