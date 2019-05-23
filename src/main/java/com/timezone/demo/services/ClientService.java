package com.timezone.demo.services;

import com.timezone.demo.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface ClientService extends CrudService<Client, Long>{


}
