package com.timezone.demo.services;



import org.springframework.security.access.annotation.Secured;

import java.util.Set;

public interface CrudService<T, ID> {
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);



}
