package com.boss.service.point;

import com.boss.domain.hibernate.HibernatePoint;
import com.boss.repository.point.PointSpringDataRepository;
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
public class PointServiceImpl implements PointService{

    private final PointSpringDataRepository pointSpringDataRepository;

    private HibernatePoint savePoint(HibernatePoint hibernatePoint) {
        return pointSpringDataRepository.save(hibernatePoint);}

    @Transactional
    @Override
    public HibernatePoint create(HibernatePoint hibernatePoint) {
        return savePoint(hibernatePoint);
    }

    @Transactional
    @Override
    public HibernatePoint delete(Long id) {
       pointSpringDataRepository.deleteById(id);
        return delete(id);
    }

    @Transactional
    @Override
    public HibernatePoint update(HibernatePoint hibernatePoint) {
        return savePoint(hibernatePoint);
    }

    @Override
    public Page<HibernatePoint> findAll(Pageable pageable) {
        return pointSpringDataRepository.findAll(pageable);
    }

    @Override
    public List<HibernatePoint> findAll() {
        return pointSpringDataRepository.findAll();
    }

    @Override
    public HibernatePoint findById(Long id) {
        Optional<HibernatePoint> result = pointSpringDataRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(
                    String.format("Point with this id \"%s\" is not found", id));
        }
    }
}
