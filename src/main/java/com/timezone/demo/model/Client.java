package com.timezone.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    @Builder
    public Client(Long id, String companyName, String address, String city, String telephone, Worker baseuser) {
        super(id);
        this.companyName = companyName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.baseuser = baseuser;
    }

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "baseuser_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Worker baseuser;

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

    public Worker getBaseuser() {
        return baseuser;
    }

    public void setBaseuser(Worker baseuser) {
        this.baseuser = baseuser;
    }

    @Override
    public String toString() {
        return "Client{" +
                "companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", workers=" + baseuser +
                '}';
    }
}
