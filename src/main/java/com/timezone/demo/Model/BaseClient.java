package com.timezone.demo.Model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "baseClients")
public class BaseClient extends BaseEntity {

    @Builder
    public BaseClient(Long id, String companyName, String address, String city, String telephone, BaseUser baseuser) {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "baseuser_id")
    private BaseUser baseuser;

}
