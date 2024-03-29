package com.boss.service.user;

import com.boss.domain.hibernate.HibernateRole;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.user.UserSpringDataRepository;
import com.boss.service.AdminService;
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
public class UserImpl implements UserService {

    private final UserSpringDataRepository userSpringDataRepository;

    private final AdminService adminService;

    @Override
    @Transactional
    public HibernateUser create(HibernateUser hibernateUser) {

        final Integer DEFAULT_USER_ROLE_ID = 1;

        hibernateUser = adminService.setRoles(hibernateUser, DEFAULT_USER_ROLE_ID);

        hibernateUser = userSpringDataRepository.save(hibernateUser);

        for (HibernateRole updatedRole : hibernateUser.getRoles()) {
            userSpringDataRepository.createRoleRow(hibernateUser.getIdUser(), updatedRole.getIdRole());
        }

        return hibernateUser;
    }

    @Override
    @Transactional
    public HibernateUser delete(Long userId) {

        Optional<HibernateUser> result = userSpringDataRepository.findById(userId);

        if (result.isPresent()) {
            HibernateUser hibernateUser = result.get();
            hibernateUser.setIsDeleted(true);
            userSpringDataRepository.save(hibernateUser);

            return userSpringDataRepository.findById(userId).get();
        } else {
            throw new EntityNotFoundException(
                    String.format("User with this id \"%s\" is not found", userId));
        }
    }

    @Override
    @Transactional
    public HibernateUser update(HibernateUser hibernateUser) {

        return userSpringDataRepository.save(hibernateUser);
    }

    @Override
    public Page<HibernateUser> findAll(Pageable pageable) {
        return userSpringDataRepository.findAll(pageable);
    }

    @Override
    public List<HibernateUser> findAll() {
        return userSpringDataRepository.findAll();
    }

    public HibernateUser findById(Long userId) {
        Optional<HibernateUser> result = userSpringDataRepository.findById(userId);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(
                    String.format("User with this id \"%s\" not found", userId));
        }
    }
}
