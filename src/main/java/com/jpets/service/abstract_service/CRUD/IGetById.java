package com.jpets.service.abstract_service.CRUD;

public interface IGetById<T,ID> {
    public T getById(ID id);
}
