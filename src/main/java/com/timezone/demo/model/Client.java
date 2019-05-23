package com.timezone.demo.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
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
