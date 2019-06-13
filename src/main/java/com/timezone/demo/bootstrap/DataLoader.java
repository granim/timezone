package com.timezone.demo.bootstrap;

import com.timezone.demo.model.Client;
import com.timezone.demo.model.Coworker;
import com.timezone.demo.model.Role;
import com.timezone.demo.model.User;
import com.timezone.demo.repositories.UserRepository;
import com.timezone.demo.services.ClientService;
import com.timezone.demo.services.CoWorkerService;
import com.timezone.demo.services.UserService;
import com.timezone.demo.services.WorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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


        User grant = new User();
        grant.setFirstName("James");
        grant.setLastName("Murray");
        grant.setRoles(Arrays.asList(new Role("ROLE_USER")));
        grant.setEmail("grant@gmail.com");
        grant.setPassword(passwordEncoder.encode("123"));

        User userTwo = new User();
        userTwo.setFirstName("Havi");
        userTwo.setLastName("Tram");
        userTwo.setRoles(Arrays.asList(new Role("ROLE_USER")));
        userTwo.setEmail("a@gmail.com");
        userTwo.setPassword(passwordEncoder.encode("123"));

        Client client1 = new Client();
        client1.setCompanyName("Telemundo");
        client1.setTelephone("123");
        client1.setCity("Tamps");
        client1.setAddress("234 Hulu");
        client1.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        client1.setUser(grant);
        clientService.save(client1);
        grant.addClient(client1);


        Coworker james = new Coworker();

        james.setfName("James");
        james.setlName("Hoffa");
        james.setTelephone("4563214654");
        james.setCity("Orlando");
        james.setAddress("15698 Set Into Drive");
        james.setUser(grant);
        coWorkerService.save(james);
        grant.addCoworker(james);

        Coworker john = new Coworker();

        john.setfName("John");
        john.setlName("Hoffa");
        john.setTelephone("4563214654");
        john.setCity("Orlando");
        john.setAddress("15698 Set Into Drive");
        john.setUser(grant);
        coWorkerService.save(james);
        coWorkerService.save(john);
        grant.addCoworker(james);
        grant.addCoworker(john);


        userService.save(grant);
        userService.save(userTwo);
        System.out.println("Loaded in Worker");


        /*User userOne = new User();
        userOne.setActive(1);
        userOne.setPassword(passwordEncoder.encode("123"));
        userOne.setUserName("kim");
        userOne.setPermissions("ACCESS");
        userOne.setRoles("ADMIN");
        userRepository.save(userOne);
        System.out.println("added in User");
        System.out.println(userService.findByUserName("kim"));
        System.out.println(userRepository.findByUserName("kim"));*/
    }





}
