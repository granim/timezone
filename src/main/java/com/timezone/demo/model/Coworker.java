package com.timezone.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coworkers")
public class Coworker extends Person {

    @Builder
    public Coworker(Long id, String firstName, String lastName, String address, String city, String telephone, Worker baseuser) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.baseuser = baseuser;
    }

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "baseuser_id")
    private Worker baseuser;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Worker getBaseuser() {
        return baseuser;
    }

    public void setBaseuser(Worker baseuser) {
        this.baseuser = baseuser;
    }

    @Override
    public String toString() {
        return  getFirstName() ;
    }
}
