package com.timezone.demo.Repositories;

import com.timezone.demo.Model.Coworker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoWorkerRepository extends CrudRepository<Coworker, Long> {
}
