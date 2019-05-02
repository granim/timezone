package com.timezone.demo.Model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "coworkers")
public class Coworker extends Person {

    @Builder
    public Coworker(Long id, String firstName, String lastName, String address, String city, String telephone, BaseUser baseuser) {
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
    private BaseUser baseuser;

}
