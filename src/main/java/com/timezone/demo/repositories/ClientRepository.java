package com.timezone.demo.repositories;

import com.timezone.demo.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByCompanyName(String companyName);

    List<Client> findAllByCompanyNameLike(String companyName);

}
