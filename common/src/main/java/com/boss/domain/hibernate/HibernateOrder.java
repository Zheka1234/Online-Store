package com.boss.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Cacheable
@Table(name = "order")
public class HibernateOrder {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_phone")
    private Long idPhone;

    @Column(name = "id_point")
    private Long idPoint;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "total_sum")
    private Double totalSum;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;




}
