package com.poly.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import com.poly.type.ERole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String fullName;
    private String photo;
    private Boolean activated=Boolean.TRUE;
    private ERole role = ERole.ROLE_USER;
}
