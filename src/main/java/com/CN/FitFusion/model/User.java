package com.CN.FitFusion.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private int age;

    private String gender;

    private Long contactNo;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
     joinColumns =  @JoinColumn(name = "user",referencedColumnName = "id"),
     inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
    private Set< Role > roles = new HashSet<>();

    // List< Exercise > exerciseList (OneToMany)

    // List< Diet > diets (OneToMany)
}
