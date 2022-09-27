package com.boss.repository.phone;

import com.boss.domain.Phone;
import com.boss.repository.CRUDRepository;

import java.util.Map;

public interface PhoneRepositoryInterface extends CRUDRepository <Long, Phone> {

    Map<String, Object> getMapOfPhone();
}
