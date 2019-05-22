package com.timezone.demo.services.springdatajpa;

import com.timezone.demo.model.Coworker;
import com.timezone.demo.repositories.CoWorkerRepository;
import com.timezone.demo.services.CoWorkerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("default")
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
