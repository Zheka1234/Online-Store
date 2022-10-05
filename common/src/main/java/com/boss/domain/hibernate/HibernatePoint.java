package com.boss.domain.hibernate;


import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Cacheable("delivery_point")
@Table(name = "delivery_point")
public class HibernatePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_point")
    private Long idPoint;

    @Column(name = "address_point")
    private String addressPoint;

    @Column(name = "hours")
    private String hours;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;
}
