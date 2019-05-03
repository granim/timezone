package com.timezone.demo.bootstrap;

import com.timezone.demo.model.BaseClient;
import com.timezone.demo.model.BaseUser;
import com.timezone.demo.services.BaseClientService;
import com.timezone.demo.services.BaseUserService;
import com.timezone.demo.services.CoWorkerService;
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

        BaseClient client1 = new BaseClient();
        client1.setTelephone("234234");
        client1.setCompanyName("Taldo");
        client1.setCity("Des");
        client1.setAddress("dsfsfdsf 2342");
        client1.setBaseuser(user1);
        baseClientService.save(client1);
        user1.getBaseClients().add(client1);

        baseUserService.save(user1);

        System.out.println("--------------------------------------Loaded Users--------------------------------------");
        System.out.println(user1.getBaseClients());
        System.out.println(client1.getCompanyName());
    }



}
