package com.jpets.service.abstract_service.CRUD;

public interface ICreate<RQ,RS> {
    public RS create(RQ request);
}
