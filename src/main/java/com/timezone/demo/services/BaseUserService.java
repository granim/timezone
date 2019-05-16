package com.timezone.demo.services;

import com.timezone.demo.model.Worker;


import java.util.List;

public interface BaseUserService extends CrudService<Worker, Long>{
    Worker findByLastName(String lastName);

    List<Worker> findAllByLastNameLike(String lastName);
}
