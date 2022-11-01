package com.boss.service.suppliers;


import com.boss.domain.hibernate.HibernateSuppliers;
import com.boss.repository.suppliers.SuppliersSpringDataRepository;
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
public class SuppliersServiceImpl implements SuppliersService {

    private final SuppliersSpringDataRepository repository;


    @Transactional
    @Override
    public HibernateSuppliers create(HibernateSuppliers hibernateSuppliers) {
        return saveSuppliers(hibernateSuppliers);
    }

    private HibernateSuppliers saveSuppliers(HibernateSuppliers hibernateSuppliers) {
        return repository.save(hibernateSuppliers);
    }

    @Transactional
    @Override
    public Long delete(Long id) {

        repository.deleteById(id);
        return id;
    }

    @Transactional
    @Override
    public HibernateSuppliers update(HibernateSuppliers hibernateSuppliers) {
        return saveSuppliers(hibernateSuppliers);
    }

    @Override
    public Page<HibernateSuppliers> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<HibernateSuppliers> findAll() {
        return repository.findAll();
    }

    @Override
    public HibernateSuppliers findById(Long id) {
        Optional<HibernateSuppliers> result = repository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(
                    String.format("Suppliers with this id \"%s\" is not found", id));
        }
    }
}
