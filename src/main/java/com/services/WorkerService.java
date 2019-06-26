package com.services;

import com.model.Worker;

import java.util.List;

public interface WorkerService extends CrudService<Worker, Long>{
    Worker findByLastName(String lastName);

    List<Worker> findAllByLastNameLike(String lastName);
}
