package com.CN.FitFusion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    // Set< Role > roles (ManyToMany)

    // List< Exercise > exerciseList (OneToMany)

    // List< Diet > diets (OneToMany)
}
