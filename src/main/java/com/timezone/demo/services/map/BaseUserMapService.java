package com.timezone.demo.services.map;

import com.timezone.demo.model.BaseClient;
import com.timezone.demo.model.Coworker;
import com.timezone.demo.model.Worker;
import com.timezone.demo.services.BaseClientService;
import com.timezone.demo.services.BaseUserService;
import com.timezone.demo.services.CoWorkerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
@Profile({"map"})
public class BaseUserMapService extends AbstractMapService<Worker, Long> implements BaseUserService {

    private final BaseClientService baseClientService;
    private final CoWorkerService coWorkerService;


    public BaseUserMapService(BaseClientService baseClientService, CoWorkerService coWorkerService) {
        this.baseClientService = baseClientService;
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
    public Worker save(Worker object) {
            Worker worker = null;
            if(object != null) {
                if(object.getCoworkers() != null) {
                    object.getCoworkers().forEach(coworker -> {
                        if(coworker.getId() != null) {
                            Coworker saveCoworker = coWorkerService.save(coworker);
                            coworker.setId(saveCoworker.getId());
                        } else {
                            throw new RuntimeException("Coworker id is required");
                        }
                    });
                }
                if(object.getBaseClients() != null) {
                    object.getBaseClients().forEach(baseClient -> {
                        if(baseClient.getId() != null) {
                            BaseClient savedClient = baseClientService.save(baseClient);
                            baseClient.setId(savedClient.getId());
                        } else {
                            throw new RuntimeException("Baseclient id is required");
                        }
                    });
                }
                return super.save(object);
            }
        return null;
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
