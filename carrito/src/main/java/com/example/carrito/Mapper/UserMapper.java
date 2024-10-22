package com.example.carrito.Mapper;

import com.example.carrito.DTO.ProductDTO;
import com.example.carrito.DTO.UserDTO;
import com.example.carrito.Domain.Model.Product;
import com.example.carrito.Domain.Model.Users;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDto(Users users) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setRol(users.getRol());
        userDTO.setEmail(users.getEmail());
        userDTO.setLastBuy(users.getLastBuy());
        userDTO.setVoucher(users.getVoucher());
        return userDTO;
    }

    public Users toModel(UserDTO userDTO) {
        Users users = new Users();
        users.setId(userDTO.getId());
        users.setEmail(userDTO.getEmail());
        users.setRol(userDTO.getRol());
        users.setLastBuy(userDTO.getLastBuy());
        users.setVoucher(userDTO.getVoucher());
        return users;
    }
}
