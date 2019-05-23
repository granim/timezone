package com.timezone.demo.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "baseusers")
public class Worker extends Person{

    @Builder
    public Worker(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Client> clients, Set<Coworker> coworkers) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if(clients == null || clients.size() > 0) {
            this.clients = clients;
        }
        if(coworkers == null || coworkers.size() > 0) {
            this.coworkers = coworkers;
        }

    }

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baseuser")
    private Set<Client> clients = new HashSet<>();

    @OneToMany( mappedBy = "baseuser")
    private Set<Coworker> coworkers = new HashSet<>();

    public Client getBaseClient(String name) {
        return getBaseClient(name, false);
    }

    protected Set<Coworker> getCoworkersInternal(){
        if(this.coworkers == null) {
            this.coworkers = new HashSet<>();
        }
        return this.coworkers;
    }

    protected void setCoworkersInternal(Set<Coworker> coworkers){
        this.coworkers = coworkers;
    }

    public void addCoworker(Coworker coworker){
        if(coworker.isNew()){
            getCoworkersInternal().add(coworker);
        }
        coworker.setBaseuser(this);
    }

    protected Set<Client> getClientsInternal(){
        if(this.clients == null) {
            this.clients = new HashSet<>();
        }
        return this.clients;
    }


    public void addClient(Client client){
        if(client.isNew()){
            getClientsInternal().add(client);
        }
        client.setBaseuser(this);
    }

    public Client getBaseClient(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for(Client client : clients) {
            if(!ignoreNew || !client.isNew()) {
                String compName = client.getCompanyName();
                compName = compName.toLowerCase();
                if(compName.equals(name)) {
                    return client;
                }
            }
        }
        return null;
    }

    public Coworker getCoworker(String name) {
        return getCoworker(name, false);
    }

    public Coworker getCoworker(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for(Coworker coworker : coworkers) {
            if(!ignoreNew || !coworker.isNew()) {
                String compName = coworker.getfName();
                compName = compName.toLowerCase();
                if(compName.equals(name)) {
                    return coworker;
                }
            }
        }
        return null;
    }




}
