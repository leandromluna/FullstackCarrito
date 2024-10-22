package com.example.carrito.Mapper;

import com.example.carrito.DTO.CartDTO;
import com.example.carrito.DTO.CartDetailDTO;
import com.example.carrito.DTO.ProductDTO2;
import com.example.carrito.DTO.UserDTO;
import com.example.carrito.Domain.Model.Cart;
import com.example.carrito.Domain.Model.CartDetail;
import com.example.carrito.Domain.Model.Users;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartDTO toDto(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setCartType(cart.getCartType());

        UserDTO userDto = new UserDTO();
        userDto.setId(cart.getUser().getId());
        userDto.setRol(cart.getUser().getRol());
        userDto.setEmail(cart.getUser().getEmail());
        userDto.setLastBuy(cart.getCreationDate());

        cartDTO.setUser(userDto);
        cartDTO.setTotal(cart.getTotal());
        cartDTO.setCreationDate(cart.getCreationDate());

        cartDTO.setCartDetails(cart.getCartDetails().stream()
                .map(this::toDetailDto)
                .collect(Collectors.toList()));

        return cartDTO;
    }



    public Cart toModel(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setCartType(cartDTO.getCartType());
        Users user = new Users();
        user.setId(cartDTO.getUser().getId());
        user.setRol(cartDTO.getUser().getRol());
        user.setEmail(cartDTO.getUser().getEmail());
        user.setLastBuy(cartDTO.getCreationDate());
        cart.setUser(user);
        cart.setTotal(cartDTO.getTotal());
        cart.setCreationDate(cartDTO.getCreationDate());
        cart.setCartDetails(cart.getCartDetails());
        return cart;
    }



    private CartDetailDTO toDetailDto(CartDetail detail) {
        CartDetailDTO dto = new CartDetailDTO();
        dto.setId(detail.getId());
        dto.setAmount(detail.getAmount());
        dto.setTotalPrice(detail.getTotalPrice());
        ProductDTO2 productDto = new ProductDTO2();
        productDto.setId(detail.getProduct().getId());
        productDto.setName(detail.getProduct().getNameProduct());
        productDto.setPrice(detail.getProduct().getPrice());
        dto.setProduct(productDto);
        return dto;
    }
}
