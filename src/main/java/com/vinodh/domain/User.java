package com.vinodh.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User implements Serializable{

    @Id
    @GeneratedValue
    private int user_id;

    private int active;

    private String email;

    private String last_name;

    private String name;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "UserRoleAssoc",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(User users) {
        this.active = users.getActive();
        this.email = users.getEmail();
        this.last_name = users.getLast_name();
        this.name = users.getName();
        this.roles=users.getRoles();
        this.user_id = users.getUser_id();
        this.password = users.getPassword();
    }
}
