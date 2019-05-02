package com.timezone.demo.Repositories;

import com.timezone.demo.Model.BaseUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<BaseUser, Long> {

    BaseUser findByLastName(String lastName);

    List<BaseUser> findAllByLastName(String lastName);

}
