package com.repositories;

import com.model.Coworker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoWorkerRepository extends CrudRepository<Coworker, Long> {

    Coworker findBylName(String lName);

    List<Coworker> findAllBylNameLike(String lName);


}
