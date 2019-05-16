package com.timezone.demo.repositories;

import com.timezone.demo.model.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<Worker, Long> {

    Worker findByLastName(String lastName);

    List<Worker> findAllByLastNameLike(String lastName);

}
