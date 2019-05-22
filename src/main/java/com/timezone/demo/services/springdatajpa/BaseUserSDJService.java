package com.timezone.demo.services.springdatajpa;

import com.timezone.demo.model.Worker;
import com.timezone.demo.repositories.ClientRepository;
import com.timezone.demo.repositories.CoWorkerRepository;
import com.timezone.demo.repositories.UserRepository;
import com.timezone.demo.services.BaseUserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("default")
public class BaseUserSDJService implements BaseUserService {

    private final UserRepository userRepository;
    private final CoWorkerRepository coWorkerRepository;
    private final ClientRepository clientRepository;

    public BaseUserSDJService(UserRepository userRepository, CoWorkerRepository coWorkerRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.coWorkerRepository = coWorkerRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Worker findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<Worker> findAllByLastNameLike(String lastName) {
        return userRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Worker> findAll() {
        Set<Worker> workers = new HashSet<>();
        userRepository.findAll().forEach(workers::add);
        return workers;
    }

    @Override
    public Worker findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public Worker save(Worker object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(Worker object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
