package com.boss.domain.hibernate;


import com.boss.domain.Gender;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "users")
public class HibernateUser {

    @Id
    private Long idUser;

    @Column(name = "name_users")
    private String nameUsers;

    @Column
    private String surnameUsers;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column
    private Double buys;

    @Column(name = "login_user")
    private String loginUser;

    @Column(name = "password_users")
    private String passwordUsers;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;



}
