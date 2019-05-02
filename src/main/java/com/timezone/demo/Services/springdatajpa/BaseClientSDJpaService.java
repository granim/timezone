package com.timezone.demo.Services.springdatajpa;

import com.timezone.demo.Model.BaseClient;
import com.timezone.demo.Repositories.ClientRepository;
import com.timezone.demo.Services.BaseClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class BaseClientSDJpaService implements BaseClientService {

    private final ClientRepository clientRepository;

    public BaseClientSDJpaService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<BaseClient> findAll() {
        Set<BaseClient> clients = new HashSet<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public BaseClient findById(Long aLong) {
        return clientRepository.findById(aLong).orElse(null);
    }

    @Override
    public BaseClient save(BaseClient object) {
        return clientRepository.save(object);
    }

    @Override
    public void delete(BaseClient object) {
        clientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }
}
