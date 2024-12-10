package com.jpets.service.abstract_service.CRUD;

public interface IUpdate<T,ID>{
    public T update(T entityUpdate, ID id);
}
