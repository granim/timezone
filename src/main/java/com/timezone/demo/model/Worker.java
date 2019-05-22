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
    public Worker(Long id, String firstName, String lastName, String address, String city, String telephone, Set<BaseClient> baseClients, Set<Coworker> coworkers) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if(baseClients == null || baseClients.size() > 0) {
            this.baseClients = baseClients;
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
    private Set<BaseClient> baseClients = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "baseuser")
    private Set<Coworker> coworkers = new HashSet<>();

    public BaseClient getBaseClient(String name) {
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



    public BaseClient getBaseClient(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for(BaseClient baseClient : baseClients) {
            if(!ignoreNew || !baseClient.isNew()) {
                String compName = baseClient.getCompanyName();
                compName = compName.toLowerCase();
                if(compName.equals(name)) {
                    return baseClient;
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
                String compName = coworker.getFirstName();
                compName = compName.toLowerCase();
                if(compName.equals(name)) {
                    return coworker;
                }
            }
        }
        return null;
    }




}
