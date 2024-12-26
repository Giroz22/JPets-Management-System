package com.jpets.infrastructure.abstract_service.CRUD;

public interface IUpdate<RQ,RS,ID>{
    public RS update(RQ request, ID id);
}
