package com.boss.repository.Phone;

import com.boss.domain.hibernate.HibernatePhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneSpringDataRepository extends JpaRepository<HibernatePhone, Long>{

}
