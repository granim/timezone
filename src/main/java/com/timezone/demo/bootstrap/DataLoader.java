package com.timezone.demo.bootstrap;

import com.timezone.demo.model.Client;
import com.timezone.demo.model.Coworker;
import com.timezone.demo.model.User;
import com.timezone.demo.model.Worker;
import com.timezone.demo.repositories.UserRepository;
import com.timezone.demo.services.ClientService;
import com.timezone.demo.services.CoWorkerService;
import com.timezone.demo.services.UserService;
import com.timezone.demo.services.WorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class DataLoader implements CommandLineRunner {

   private final ClientService clientService;
   private final CoWorkerService coWorkerService;
   private final WorkerService workerService;
   private final UserService userService;
   private PasswordEncoder passwordEncoder;
   private UserRepository userRepository;

    public DataLoader(ClientService clientService, CoWorkerService coWorkerService, WorkerService workerService, UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.clientService = clientService;
        this.coWorkerService = coWorkerService;
        this.workerService = workerService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        Worker grant = new Worker();
        grant.setFirstName("James");
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
        client1.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        client1.setWorker(grant);
        clientService.save(client1);
        grant.getClients().add(client1);

        /*Client client2 = new Client();
        client2.setCompanyName("Texico");
        client2.setTelephone("123 2334");
        client2.setCity("Tampa");
        client2.setAddress("234 Hulu Rd");
        client2.setWorker(grant);
        clientService.save(client2);
        grant.getClients().add(client2);*/

        Coworker james = new Coworker();

        james.setfName("James");
        james.setlName("Hoffa");
        james.setTelephone("4563214654");
        james.setCity("Orlando");
        james.setAddress("15698 Set Into Drive");
        james.setWorker(grant);
        coWorkerService.save(james);
        grant.getCoworkers().add(james);

        Coworker john = new Coworker();

        john.setfName("John");
        john.setlName("Hoffa");
        john.setTelephone("4563214654");
        john.setCity("Orlando");
        john.setAddress("15698 Set Into Drive");
        john.setWorker(grant);
        coWorkerService.save(james);
        coWorkerService.save(john);
        grant.getCoworkers().add(james);
        grant.getCoworkers().add(john);


        workerService.save(grant);
        workerService.save(workerTwo);
        System.out.println("Loaded in Worker");
        userRepository.deleteAll();

        User userOne = new User();
        userOne.setActive(1);
        userOne.setPassword(passwordEncoder.encode("123"));
        userOne.setUserName("kim");
        userOne.setPermissions("ACCESS");
        userOne.setRoles("ADMIN");
        userRepository.save(userOne);
        System.out.println("added in User");
        System.out.println(userService.findByUserName("kim"));
        System.out.println(userRepository.findByUserName("kim"));
    }





}
