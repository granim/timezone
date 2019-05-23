package com.timezone.demo.bootstrap;

import com.timezone.demo.model.Client;
import com.timezone.demo.model.Coworker;
import com.timezone.demo.model.Worker;
import com.timezone.demo.services.BaseUserService;
import com.timezone.demo.services.ClientService;
import com.timezone.demo.services.CoWorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

   private final ClientService clientService;
   private final CoWorkerService coWorkerService;
   private final BaseUserService baseUserService;

    public DataLoader(ClientService clientService, CoWorkerService coWorkerService, BaseUserService baseUserService) {
        this.clientService = clientService;
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

        Worker workerTwo = new Worker();
        workerTwo.setFirstName("Havi");
        workerTwo.setLastName("Tram");
        workerTwo.setAddress("3157 Brent");
        workerTwo.setCity("Orlando");
        workerTwo.setTelephone("31456987");

        Client client1 = new Client();
        client1.setCompanyName("Telemundo");
        client1.setTelephone("123");
        client1.setCity("Tamps");
        client1.setAddress("234 Hulu");
        client1.setBaseuser(grant);
        clientService.save(client1);
        grant.getClients().add(client1);

        Coworker james = new Coworker();

        james.setfName("James");
        james.setlName("Hoffa");
        james.setTelephone("4563214654");
        james.setCity("Orlando");
        james.setAddress("15698 Set Into Drive");
        james.setBaseuser(grant);
        coWorkerService.save(james);
        grant.getCoworkers().add(james);

        Coworker john = new Coworker();

        john.setfName("John");
        john.setlName("Hoffa");
        john.setTelephone("4563214654");
        john.setCity("Orlando");
        john.setAddress("15698 Set Into Drive");
        john.setBaseuser(grant);
        coWorkerService.save(james);
        coWorkerService.save(john);
        grant.getCoworkers().add(james);
        grant.getCoworkers().add(john);


        baseUserService.save(grant);
        baseUserService.save(workerTwo);
        System.out.println("Loaded in Worker");
    }





}
