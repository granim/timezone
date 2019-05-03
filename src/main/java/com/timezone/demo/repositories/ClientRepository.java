package com.timezone.demo.repositories;

import com.timezone.demo.model.BaseClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<BaseClient, Long> {
}
