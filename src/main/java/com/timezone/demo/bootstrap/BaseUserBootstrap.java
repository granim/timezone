package com.timezone.demo.bootstrap;

import com.timezone.demo.model.BaseUser;
import com.timezone.demo.repositories.ClientRepository;
import com.timezone.demo.repositories.CoWorkerRepository;
import com.timezone.demo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Profile("default")
public class BaseUserBootstrap implements ApplicationListener<ContextRefreshedEvent> {


    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final CoWorkerRepository coWorkerRepository;

    public BaseUserBootstrap(UserRepository userRepository, ClientRepository clientRepository, CoWorkerRepository coWorkerRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.coWorkerRepository = coWorkerRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("BootStrap");
        userRepository.saveAll(getBaseUsers());
    }

    private List<BaseUser> getBaseUsers(){

        List<BaseUser> users = new ArrayList<>(1);

        BaseUser userOne = new BaseUser();
        userOne.setFirstName("Grat");
        userOne.setLastName("Mur");
        userOne.setAddress("sdfsd 234234");
        userOne.setCity("IO");
        userOne.setTelephone("56++5+5564564");

        users.add(userOne);
        return users;
    }




}
