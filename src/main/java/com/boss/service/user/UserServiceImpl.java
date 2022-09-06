package com.boss.service.user;

import com.boss.domain.User;
import com.boss.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Map<String, Object> getUserStats() {
        return userRepository.getUserStats();
    }

    @Override
    public User create(User object) {
        return userRepository.create(object);
    }

    @Override
    public Long delete(Long id) {
        return this.userRepository.delete(id);
    }

    @Override
    public List<User> search(int limit, int offset) {
        return userRepository.findAll(limit, offset);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }
}
