package com.boss.service.phone;

import com.boss.domain.Phone;


import java.util.List;
import java.util.Map;

public interface PhoneService {


    List<Phone> findAll();

    Map<String, Object> getMapOfPhone();

    Phone create(Phone object);


}
