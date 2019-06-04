package com.timezone.demo.services.springdatajpa;

import com.timezone.demo.model.Worker;
import com.timezone.demo.repositories.ClientRepository;
import com.timezone.demo.repositories.CoWorkerRepository;
import com.timezone.demo.repositories.WorkerRepository;
import com.timezone.demo.services.WorkerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("default")
public class WorkerSDJService implements WorkerService {

    private final WorkerRepository workerRepository;
    private final CoWorkerRepository coWorkerRepository;
    private final ClientRepository clientRepository;

    public WorkerSDJService(WorkerRepository workerRepository, CoWorkerRepository coWorkerRepository, ClientRepository clientRepository) {
        this.workerRepository = workerRepository;
        this.coWorkerRepository = coWorkerRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Worker findByLastName(String lastName) {
        return workerRepository.findByLastName(lastName);
    }

    @Override
    public List<Worker> findAllByLastNameLike(String lastName) {
        return workerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Worker> findAll() {
        Set<Worker> workers = new HashSet<>();
        workerRepository.findAll().forEach(workers::add);
        return workers;
    }

    @Override
    public Worker findById(Long aLong) {
        return workerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Worker save(Worker object) {
        return workerRepository.save(object);
    }

    @Override
    public void delete(Worker object) {
        workerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        workerRepository.deleteById(aLong);
    }
}
