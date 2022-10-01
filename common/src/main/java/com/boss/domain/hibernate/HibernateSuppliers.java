package com.boss.domain.hibernate;


import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(exclude = {
        "supplied_phone"
})
@Cacheable("suppliers")
@Table(name = "suppliers")
public class HibernateSuppliers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suppliers")
    private Long idSuppliers;

    @Column(name = "name_suppliers")
    private String nameSuppliers;

    @Column(name = "address_suppliers")
    private String addressSuppliers;

    @Column(name = "phone_suppliers")
    private String phoneSuppliers;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "modification_date")
    private Timestamp modificationDate;

}
