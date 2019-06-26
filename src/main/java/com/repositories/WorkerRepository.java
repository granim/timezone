package com.repositories;

import com.model.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkerRepository extends CrudRepository<Worker, Long> {

    Worker findByLastName(String lastName);

    List<Worker> findAllByLastNameLike(String lastName);

}
