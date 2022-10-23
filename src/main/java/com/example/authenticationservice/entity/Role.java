package com.example.authenticationservice.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role", unique = true)
    private String role;

//    @ManyToMany(mappedBy = "roles")
    @ManyToMany
    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
//    @ManyToMany
    Set<User> users = new HashSet<>();
    public void addUser(User u){
        users.add(u);
    }
}
