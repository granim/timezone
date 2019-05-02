package com.timezone.demo.Services.map;

import com.timezone.demo.Model.Coworker;
import com.timezone.demo.Services.CoWorkerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@Profile({"default", "map"})
public class CoWorkerMapService extends AbstractMapService<Coworker, Long> implements CoWorkerService {

    @Override
    public Set<Coworker> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Coworker object) {
         super.delete(object);
    }

    @Override
    public Coworker save(Coworker object) {
        return super.save(object);
    }

    @Override
    public Coworker findById(Long id) {
        return super.findById(id);
    }
}
