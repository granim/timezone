package com.timezone.demo.Services;

import com.timezone.demo.Model.BaseUser;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BaseUserService extends CrudService<BaseUser, Long>{
    BaseUser findByLastName(String lastName);

    List<BaseUser> findAllByLastName(String lastName);

}
