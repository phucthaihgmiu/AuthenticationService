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

    @PrimaryKey
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String role;

}
