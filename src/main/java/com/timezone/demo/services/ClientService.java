package com.timezone.demo.services;

import com.timezone.demo.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimeZone;

@Component
@Service
public interface ClientService extends CrudService<Client, Long>{

    Client findByCompanyName(String companyName);

    List<Client> findAllByCompanyNameLike(String companyNamy);

    Client findByTimeZone(TimeZone timeZone);


}
