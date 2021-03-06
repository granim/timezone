package com.services.map;

import com.model.Worker;
import com.services.ClientService;
import com.services.CoWorkerService;
import com.services.WorkerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
@Profile({"map"})
public class WorkerMapService extends AbstractMapService<Worker, Long> implements WorkerService {

    private final ClientService clientService;
    private final CoWorkerService coWorkerService;


    public WorkerMapService(ClientService clientService, CoWorkerService coWorkerService) {
        this.clientService = clientService;
        this.coWorkerService = coWorkerService;
    }

    @Override
    public Set<Worker> findAll() {
        return super.findAll();
    }

    @Override
    public Worker findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Worker save(Worker object){
        return super.save(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Worker object) {
        super.delete(object);
    }

    @Override
    public Worker findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(worker -> worker.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Worker> findAllByLastNameLike(String lastName) {
        //TODO - impl
        return null;

    }
}
