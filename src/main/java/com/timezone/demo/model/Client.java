package com.timezone.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.TimeZone;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    @Builder
    public Client(Long id, String companyName, String address, String city, String telephone, TimeZone timeZone, Worker worker) {
        super(id);
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.timeZone = timeZone;
        this.worker = worker;
    }

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "timeZone")
    private TimeZone timeZone;
    @Enumerated(value = EnumType.STRING)
    private TimeZoneList timeZoneList;

    public TimeZoneList getTimeZoneList() {
        return timeZoneList;
    }

    public void setTimeZoneList(TimeZoneList timeZoneList) {
        this.timeZoneList = timeZoneList;
    }

    @ManyToOne
    @JoinColumn(name = "baseuser_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Worker worker;

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

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

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "Client{" +
                "companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", workers=" + worker +
                '}';
    }

}
