package com.timezone.demo.services.map;

import com.timezone.demo.model.BaseClient;
import com.timezone.demo.model.BaseUser;
import com.timezone.demo.model.Coworker;
import com.timezone.demo.services.BaseClientService;
import com.timezone.demo.services.BaseUserService;
import com.timezone.demo.services.CoWorkerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
@Profile({"default", "map"})
public class BaseUserMapService extends AbstractMapService<BaseUser, Long> implements BaseUserService {

    private final BaseClientService baseClientService;
    private final CoWorkerService coWorkerService;

    public BaseUserMapService(BaseClientService baseClientService, CoWorkerService coWorkerService) {
        this.baseClientService = baseClientService;
        this.coWorkerService = coWorkerService;
    }

    @Override
    public BaseUser findByLastName(String lastName) {
        return null;
    }

    @Override
    public List<BaseUser> findAllByLastName(String lastName) {
        return null;
    }

    @Override
    public Set<BaseUser> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(BaseUser object) {
        super.delete(object);
    }

    @Override
    public BaseUser save(BaseUser object) {
            BaseUser baseUser = null;
            if(object != null) {
                if(object.getCoworkers() != null) {
                    object.getCoworkers().forEach(coworker -> {
                        if(coworker.getId() == null) {
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
    public BaseUser findById(Long id) {
        return super.findById(id);
    }
}
