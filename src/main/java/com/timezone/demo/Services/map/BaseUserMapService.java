package com.timezone.demo.Services.map;

import com.timezone.demo.Model.BaseClient;
import com.timezone.demo.Model.BaseUser;
import com.timezone.demo.Model.Coworker;
import com.timezone.demo.Services.BaseClientService;
import com.timezone.demo.Services.BaseUserService;
import com.timezone.demo.Services.CoWorkerService;
import org.apache.tomcat.jni.User;
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
                        if(baseClient.getId() == null) {
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
