package com.timezone.demo.Services.map;

import com.timezone.demo.Model.BaseClient;
import com.timezone.demo.Services.BaseClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default", "map"})
public class BaseClientMapService extends AbstractMapService<BaseClient, Long> implements BaseClientService {
    @Override
    public Set<BaseClient> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(BaseClient object) {
        super.delete(object);
    }

    @Override
    public BaseClient save(BaseClient object) {
        return super.save(object);
    }

    @Override
    public BaseClient findById(Long id) {
        return super.findById(id);
    }
}
