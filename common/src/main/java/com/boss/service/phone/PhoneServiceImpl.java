package com.boss.service.phone;

import com.boss.domain.hibernate.HibernatePhone;
import com.boss.repository.Phone.PhoneSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneSpringDataRepository phoneSpringDataRepository;

    private HibernatePhone savePhone(HibernatePhone hibernatePhone) {
        return phoneSpringDataRepository.save(hibernatePhone);}

    @Override
    @Transactional
    public HibernatePhone create(HibernatePhone hibernatePhone) {
        return savePhone(hibernatePhone);
    }

    @Override
    @Transactional
    public HibernatePhone delete(Long id) {
        phoneSpringDataRepository.deleteById(id);
        return delete(id);
    }

    @Override
    @Transactional
    public HibernatePhone update(HibernatePhone hibernatePhone) {
        return savePhone(hibernatePhone);
    }

    @Override
    public Page<HibernatePhone> findAll(Pageable pageable) {
        return phoneSpringDataRepository.findAll(pageable);
    }

    @Override
    public List<HibernatePhone> findAll() {
        return phoneSpringDataRepository.findAll();
    }

    @Override
    public HibernatePhone findById(Long phoneId) {
        Optional<HibernatePhone> result = phoneSpringDataRepository.findById(phoneId);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(
                    String.format("Phone with this id \"%s\" is not found", phoneId));
        }
    }



}
