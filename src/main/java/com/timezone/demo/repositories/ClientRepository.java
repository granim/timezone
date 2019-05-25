package com.timezone.demo.repositories;

import com.timezone.demo.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.TimeZone;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByCompanyName(String companyName);

    List<Client> findAllByCompanyNameLike(String companyName);

    Client findByTimeZone(TimeZone timeZone);

}
