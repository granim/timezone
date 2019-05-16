package com.timezone.demo.bootstrap;

import com.timezone.demo.model.BaseClient;
import com.timezone.demo.model.Coworker;
import com.timezone.demo.model.Worker;
import com.timezone.demo.services.BaseClientService;
import com.timezone.demo.services.BaseUserService;
import com.timezone.demo.services.CoWorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class DataLoader implements CommandLineRunner {

   private final BaseClientService baseClientService;
   private final CoWorkerService coWorkerService;
   private final BaseUserService baseUserService;

    public DataLoader(BaseClientService baseClientService, CoWorkerService coWorkerService, BaseUserService baseUserService) {
        this.baseClientService = baseClientService;
        this.coWorkerService = coWorkerService;
        this.baseUserService = baseUserService;
    }


    @Override
    public void run(String... args) throws Exception {


        Worker grant = new Worker();
        grant.setFirstName("Grant");
        grant.setLastName("Murray");
        grant.setAddress("234 Debar");
        grant.setCity("Melbourne");
        grant.setTelephone("3216894");


        BaseClient client1 = new BaseClient();
        client1.setCompanyName("Telemundo");
        client1.setTelephone("123");
        client1.setCity("Tamps");
        client1.setAddress("234 Hulu");
        client1.setBaseuser(grant);
        baseClientService.save(client1);
        grant.getBaseClients().add(client1);

        Coworker james = new Coworker();

        james.setFirstName("James");
        james.setLastName("Hoffa");
        james.setTelephone("4563214654");
        james.setCity("Orlando");
        james.setAddress("15698 Set Into Drive");
        james.setBaseuser(grant);
        coWorkerService.save(james);
        grant.getCoworkers().add(james);

        baseUserService.save(grant);
        System.out.println("Loaded in Worker");
    }





}
