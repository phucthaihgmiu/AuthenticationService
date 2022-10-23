package com.example.authenticationservice.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @ManyToMany
//    @JoinTable(
//        name = "user_role",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "role_id")
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role r){
        roles.add(r);
    }

    public List<String> getRolesAsString(){
        return this.getRoles().stream().map(r -> r.getRole()).toList();
    }
}
