package com.boss.domain.hibernate;


import com.boss.domain.Credentials;
import com.boss.domain.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "roles", "order"
})
@Table(name = "users")
public class HibernateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "name_users")
    private String nameUsers;

    @Column(name = "surname_users")
    private String surnameUsers;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "buys")
    private Double buys;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "login_user")),
            @AttributeOverride(name = "password", column = @Column(name = "password_users"))
    })
    private Credentials credentials;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<HibernateRole> roles;


    @OneToMany(mappedBy="idUser", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<HibernateOrder> orders;


}