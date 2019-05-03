package com.timezone.demo.services.springdatajpa;

import com.timezone.demo.model.BaseUser;
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
@Profile("springdataJPA")
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
    public BaseUser findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<BaseUser> findAllByLastName(String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    @Override
    public Set<BaseUser> findAll() {
        Set<BaseUser> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public BaseUser findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public BaseUser save(BaseUser object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(BaseUser object) {
        userRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}
