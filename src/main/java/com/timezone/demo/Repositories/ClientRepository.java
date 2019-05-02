package com.timezone.demo.Repositories;

import com.timezone.demo.Model.BaseClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<BaseClient, Long> {
}
