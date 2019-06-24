package com.timezone.demo.model;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    //TODO creat method to expose client and coworker list, make each private
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public Set<Client> clients = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    public Set<Coworker> coworkers = new HashSet<>();


    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Builder
    public User(String firstName, String lastName, String email, String password, Collection<Role> roles, Set<Client> clients, Set<Coworker> coworkers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        if(clients == null || clients.size() > 0) {
            this.clients = clients;
        }
        if(coworkers == null || coworkers.size() > 0) {
            this.coworkers = coworkers;
        }
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getFirstName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

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
        coworker.setUser(this);
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
        client.setUser(this);
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role roles: getRoles()) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roles);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "*********" + '\'' +
                ", roles=" + roles +
                '}';
    }

}