package com.jpets.service.abstract_service.CRUD;

public interface IGetById<RS,ID> {
    public RS getById(ID id);
}
