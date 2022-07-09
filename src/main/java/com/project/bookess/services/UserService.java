package com.project.bookess.services;

import com.project.bookess.model.User;
import com.project.bookess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public boolean updateUser(User user) {
        Optional<User> optUser = userRepository.findById(user.getId());
        if(optUser.isPresent()) {
            user.setPassword(optUser.get().getPassword());
            User u = userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id) {
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }
}
