package com.jpets.infrastructure.abstract_service.CRUD;

public interface ICreate<RQ,RS> {
    public RS create(RQ request);
}
