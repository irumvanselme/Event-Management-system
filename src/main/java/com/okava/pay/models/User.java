package com.okava.pay.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okava.pay.audits.Model;
import com.okava.pay.models.enums.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends Model {

    @Column(name = "full_names")
    private String fullNames;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "national_id", unique = true)
    private String nationalId;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "location")
    private String location;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERole role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_tags", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags = new ArrayList<>();
}
