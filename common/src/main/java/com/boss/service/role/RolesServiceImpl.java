package com.boss.service.role;

import com.boss.domain.hibernate.HibernateRole;
import com.boss.repository.roles.RoleSpringDataRepository;
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
public class RolesServiceImpl implements RolesService{

    private final RoleSpringDataRepository repository;

    private HibernateRole saveRole(HibernateRole hibernateRole) {

        return repository.save(hibernateRole);
    }
    @Override
    @Transactional
    public HibernateRole create(HibernateRole hibernateRole) {
        return saveRole(hibernateRole);
    }

    @Override
    @Transactional
    public HibernateRole delete(Integer roleId) {
        HibernateRole hibernateRole = findById(roleId);

        repository.save(hibernateRole);

        return findById(roleId);
    }


    @Override
    @Transactional
    public HibernateRole update(HibernateRole hibernateRole) {
        return saveRole(hibernateRole);
    }

    @Override
    public Page<HibernateRole> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<HibernateRole> findAll() {
        return repository.findAll();
    }

    @Override
    public HibernateRole findById(Integer id) {
        Optional<HibernateRole> result = repository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(
                    String.format("Role with this id \"%s\" is not found", id));
        }
    }

    @Override
    public List<Object[]> findOnlyRoles() {
        return repository.findOnlyRoles();
    }
}
