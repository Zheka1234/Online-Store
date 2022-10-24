package com.boss.repository.Phone;

import com.boss.domain.hibernate.HibernatePhone;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PhoneSpringDataRepository extends JpaRepository<HibernatePhone, Long>{

    @Cacheable("phone")
    Page<HibernatePhone> findAll(Pageable pageable);


}
