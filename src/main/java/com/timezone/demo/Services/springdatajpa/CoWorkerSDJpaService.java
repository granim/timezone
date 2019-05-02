package com.timezone.demo.Services.springdatajpa;

import com.timezone.demo.Model.Coworker;
import com.timezone.demo.Repositories.CoWorkerRepository;
import com.timezone.demo.Services.CoWorkerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class CoWorkerSDJpaService implements CoWorkerService {

    private final CoWorkerRepository coWorkerRepository;

    public CoWorkerSDJpaService(CoWorkerRepository coWorkerRepository) {
        this.coWorkerRepository = coWorkerRepository;
    }

    @Override
    public Set<Coworker> findAll() {
        Set<Coworker> coworkers = new HashSet<>();
        coWorkerRepository.findAll().forEach(coworkers::add);
        return coworkers;
    }

    @Override
    public Coworker findById(Long aLong) {
        return coWorkerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Coworker save(Coworker object) {
        return coWorkerRepository.save(object);
    }

    @Override
    public void delete(Coworker object) {
        coWorkerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        coWorkerRepository.deleteById(aLong);
    }
}
