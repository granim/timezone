package com.timezone.demo.repositories;

import com.timezone.demo.model.Coworker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoWorkerRepository extends CrudRepository<Coworker, Long> {
}
