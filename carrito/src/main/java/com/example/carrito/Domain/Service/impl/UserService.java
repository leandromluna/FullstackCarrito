package com.example.carrito.Domain.Service.impl;

import com.example.carrito.DTO.UserDTO;
import com.example.carrito.Domain.Model.Users;
import com.example.carrito.Domain.Service.iUserService;
import com.example.carrito.Mapper.UserMapper;
import com.example.carrito.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements iUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userMapper.toDto(user);
    }
}
