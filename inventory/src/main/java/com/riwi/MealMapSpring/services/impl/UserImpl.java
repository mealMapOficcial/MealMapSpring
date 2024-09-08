package com.riwi.MealMapSpring.services.impl;

import com.riwi.RiwiMovies.dtos.request.Users.UserWithoutStatus;
import com.riwi.RiwiMovies.dtos.response.Users.UserWithoutPassword;
import com.riwi.RiwiMovies.entities.UserEntity;
import com.riwi.RiwiMovies.repositories.interfaces.UserRepository;
import com.riwi.RiwiMovies.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void archive(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        user.setStatus(false);
        userRepository.save(user);

        userRepository.deleteById(id);
    }

    @Override
    public List<UserWithoutPassword> readAll() {

        List<UserEntity> userEntity = userRepository.findAll();

        List<UserWithoutPassword> usersWithoutPassword = new ArrayList<>();

        for (UserEntity user : userEntity) {
            UserWithoutPassword dto = new UserWithoutPassword();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setAge(user.getAge());
            dto.setStatus(user.getStatus());
            usersWithoutPassword.add(dto);
        }

        return usersWithoutPassword;
    }

    @Override
    public UserWithoutPassword readById(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El usuario con id " + id + " no fue encontrado"));

        UserWithoutPassword userWithoutPassword = new UserWithoutPassword();
        userWithoutPassword.setId(user.getId());
        userWithoutPassword.setName(user.getName());
        userWithoutPassword.setEmail(user.getEmail());
        userWithoutPassword.setAge(user.getAge());
        userWithoutPassword.setStatus(user.getStatus());

        return userWithoutPassword;
    }

    @Override
    public UserEntity create(UserWithoutStatus userDTO) {
        UserEntity request = new UserEntity(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getAge(),
                userDTO.getPassword(),
                true);

        return userRepository.save(request);
    }
}
