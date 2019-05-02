package com.timezone.demo.bootstrap;

import com.timezone.demo.Model.BaseClient;
import com.timezone.demo.Model.BaseUser;
import com.timezone.demo.Model.Coworker;
import com.timezone.demo.Services.BaseClientService;
import com.timezone.demo.Services.BaseUserService;
import com.timezone.demo.Services.CoWorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BaseUserService baseUserService;
    private final BaseClientService baseClientService;
    private final CoWorkerService coWorkerService;


    public DataLoader(BaseUserService baseUserService, BaseClientService baseClientService, CoWorkerService coWorkerService) {
        this.baseUserService = baseUserService;
        this.baseClientService = baseClientService;
        this.coWorkerService = coWorkerService;
    }

    @Override
    public void run(String... args) throws Exception {

        BaseUser user1 = new BaseUser();
        user1.setTelephone("1454563");
        user1.setCity("ssss");
        user1.setAddress("23424 sdfsfds");
        user1.setLastName("hhh");
        user1.setFirstName("jojo");
        baseUserService.save(user1);


        System.out.println("--------------------------------------Loaded Users--------------------------------------");

    }



}
