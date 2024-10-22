package com.example.carrito.Controller;


import com.example.carrito.DTO.UserDTO;
import com.example.carrito.Domain.Service.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private iUserService userService;

    @GetMapping()
    public ResponseEntity<UserDTO> getAuthenticatedUser() {
        UserDTO userDTO = userService.getAuthenticatedUser();
        return ResponseEntity.ok(userDTO);
    }
}
