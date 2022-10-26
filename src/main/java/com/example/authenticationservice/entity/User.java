package com.example.authenticationservice.entity;

import lombok.*;
//import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;

//import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

//@Entity
//@Table(name = "users")
////@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Getter
@Setter
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    @Column
    private UUID id;

//    @Column(unique = true)
//    @PrimaryKey
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;
//
//    @ManyToMany
////    @JoinTable(
////        name = "user_role",
////        joinColumns = @JoinColumn(name = "user_id"),
////        inverseJoinColumns = @JoinColumn(name = "role_id"))
//    @Cascade({ CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinColumn(name = "role_id")
//    private Set<Role> roles = new HashSet<>();

    @Column
    private String role;
//
//    public void addRole(Role r){
//        roles.add(r);
//    }
//
//    public List<String> getRolesAsString(){
//        return this.getRoles().stream().map(r -> r.getRole()).toList();
//    }
}
