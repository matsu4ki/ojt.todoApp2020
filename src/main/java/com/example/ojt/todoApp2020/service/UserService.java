package com.example.ojt.todoApp2020.service;

import com.example.ojt.todoApp2020.entity.User;
import com.example.ojt.todoApp2020.form.CreateUserForm;
import com.example.ojt.todoApp2020.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void save(User user) {
        if (!user.getPassword().isEmpty()) {
            //パスワードをハッシュ化
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.saveAndFlush(user);
    }

    public boolean existUserByUsername(User user) {
        var userOptional = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        return userOptional.isPresent();
    }

    public boolean existUserById(int id) {
        return userRepository.existsById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void createUserByForm(CreateUserForm createUserForm) {
        var user = new User();
        var username = createUserForm.getUsername();
        var encodedPassword = passwordEncoder.encode(createUserForm.getPassword());
        user.setUsername(username);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
