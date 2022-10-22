package com.boss.repository.Phone;

import com.boss.domain.hibernate.HibernatePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhoneSpringDataRepository extends JpaRepository<HibernatePhone, Long>{
    List<HibernatePhone> findHibernatePhoneByIdPhone(Long phoneId);

}
