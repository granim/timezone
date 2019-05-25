package com.timezone.demo.services.springdatajpa;

import com.timezone.demo.model.Client;
import com.timezone.demo.repositories.ClientRepository;
import com.timezone.demo.services.ClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

@Service
@Profile("default")
public class ClientSDJpaService implements ClientService {

    private final ClientRepository clientRepository;

    public ClientSDJpaService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Client> findAll() {
        Set<Client> clients = new HashSet<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public Client findById(Long aLong) {
        return clientRepository.findById(aLong).orElse(null);
    }

    @Override
    public Client save(Client object) {
        return clientRepository.save(object);
    }

    @Override
    public void delete(Client object) {
        clientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }

    @Override
    public Client findByCompanyName(String companyName) {
        return clientRepository.findByCompanyName(companyName);
    }

    @Override
    public List<Client> findAllByCompanyNameLike(String companyName) {
        return clientRepository.findAllByCompanyNameLike(companyName);
    }

    @Override
    public Client findByTimeZone(TimeZone timeZone) {
        return clientRepository.findByTimeZone(timeZone);
    }
    

}
