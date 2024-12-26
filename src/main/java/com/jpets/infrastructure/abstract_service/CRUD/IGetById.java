package com.jpets.infrastructure.abstract_service.CRUD;

public interface IGetById<RS,ID> {
    public RS getById(ID id);
}
