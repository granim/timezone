package com.timezone.demo.bootstrap;

import com.timezone.demo.model.BaseClient;
import com.timezone.demo.model.Worker;
import com.timezone.demo.model.Coworker;
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

    private List<Worker> getBaseUsers(){

        List<Worker> Worker = new ArrayList<>(1);

        Worker userOne = new Worker();
        userOne.setFirstName("Grat");
        userOne.setLastName("Mur");
        userOne.setAddress("sdfsd 234234");
        userOne.setCity("IO");
        userOne.setTelephone("56++5+5564564");

        Worker userTwo = new Worker();
        userTwo.setFirstName("Martha");
        userTwo.setLastName("Zelma");
        userTwo.setAddress("156 St low rd");
        userTwo.setCity("Dalles");
        userTwo.setTelephone("345354");


        Coworker worker1 = new Coworker();
        worker1.setBaseuser(userOne);
        worker1.setAddress("456 sfdsfd");
        worker1.setCity("Orlando");
        worker1.setTelephone("456 456");
        worker1.setFirstName("Jim");
        worker1.setLastName("Joe");

        BaseClient client1 = new BaseClient();
        client1.setBaseuser(userOne);
        client1.setAddress("45614s");
        client1.setCity("Melbourne");
        client1.setCompanyName("HARgeset");
        client1.setTelephone("123567");


        userOne.getCoworkers().add(worker1);
        userOne.getBaseClients().add(client1);

        Worker.add(userOne);
        Worker.add(userTwo);
        return Worker;

    }

}
