package com.timezone.demo.repositories;

import java.util.List;

public interface CustomCrudRepository<T, ID> {

    ID add(T t);

    void delete(ID id);

    boolean update(T t);

    T findOne(ID id);

    List<T> findAll();



}
