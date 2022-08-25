package com.boss.service.phone;

import com.boss.domain.Phone;


import java.util.List;
import java.util.Map;

public interface PhoneService {

    Phone update(Phone object);

    Phone findById(Long id);

    List<Phone> findAll();

    Map<String, Object> getMapOfPhone();

    Phone create(Phone object);

    Long delete (Long id);


}
