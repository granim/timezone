package com.timezone.demo.services;

import com.timezone.demo.model.BaseUser;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BaseUserService extends CrudService<BaseUser, Long>{
    BaseUser findByLastName(String lastName);

    List<BaseUser> findAllByLastName(String lastName);

}
