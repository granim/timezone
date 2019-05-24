package com.timezone.demo.services;

import com.timezone.demo.model.Coworker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoWorkerService extends CrudService<Coworker, Long> {
    Coworker findByLastName(String lName);

    List<Coworker> findAllByLastNameLike(String lName);

}
