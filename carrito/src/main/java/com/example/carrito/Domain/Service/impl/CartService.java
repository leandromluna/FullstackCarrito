package com.example.carrito.Domain.Service.impl;

import com.example.carrito.DTO.CartDTO;
import com.example.carrito.DTO.CartDetailDTO;
import com.example.carrito.DTO.UserDTO;
import com.example.carrito.Domain.Model.*;
import com.example.carrito.Domain.Service.iCartService;
import com.example.carrito.Mapper.CartMapper;
import com.example.carrito.Mapper.UserMapper;
import com.example.carrito.Repository.CartDetailRepository;
import com.example.carrito.Repository.CartRepository;
import com.example.carrito.Repository.ProductRepository;
import com.example.carrito.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements iCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public synchronized ResponseEntity<String> processPurchase(CartDTO cartDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();

            Users user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

            if(user.getVoucher() && !cartDTO.getCartType().equals(EnumCart.VIP)) cartDTO.setCartType(EnumCart.VIP);

            UserDTO userDTO = userMapper.toDto(user);
            cartDTO.setUser(userDTO);

            Cart cart = cartMapper.toModel(cartDTO);
            List<CartDetail> cartDetails = new ArrayList<>();

            int totalAmount = 0;
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (CartDetailDTO detail : cartDTO.getCartDetails()) {
                Product product = productRepository.findById(detail.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product not found: " + detail.getProduct().getId()));

                if (product.getStock() < detail.getAmount()) {
                    throw new RuntimeException("Not enough stock for product: " + product.getNameProduct());
                }

                BigDecimal productPrice = product.getPrice().multiply(new BigDecimal(detail.getAmount()));

                CartDetail cartDetail = new CartDetail();
                cartDetail.setProduct(product);
                cartDetail.setAmount(detail.getAmount());
                cartDetail.setTotalPrice(detail.getTotalPrice().compareTo(productPrice) == 0 ?
                        detail.getTotalPrice() : productPrice);
                cartDetail.setCart(cart);
                cartDetails.add(cartDetail);

                totalAmount += detail.getAmount();
                totalPrice = totalPrice.add(cartDetail.getTotalPrice());
            }

            BigDecimal discount = calculateDiscount(cartDTO.getCartType(), totalAmount, totalPrice, cartDetails, user);
            totalPrice = totalPrice.subtract(discount);

            cart.setCartDetails(cartDetails);
            cart.setUser(user);
            cart.setTotal(totalPrice);
            cartRepository.save(cart);
            user.setLastBuy(cart.getCreationDate());
            if(user.getVoucher() && cart.getCartType().equals(EnumCart.VIP)) user.setVoucher(false);
            userRepository.save(user);

            for (CartDetail cartDetail : cartDetails) {
                Product product = cartDetail.getProduct();
                product.setStock(product.getStock() - cartDetail.getAmount());
                productRepository.save(product);
            }

            return ResponseEntity.ok("Purchase processed successfully. Total after discount: " + totalPrice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    private BigDecimal calculateDiscount(EnumCart cartType, int totalAmount, BigDecimal totalPrice, List<CartDetail> cartDetails, Users user) {
        BigDecimal discount = BigDecimal.ZERO;

        if (totalAmount == 4) {
            discount = discount.add(totalPrice.multiply(BigDecimal.valueOf(0.25)));
        }

        if (totalAmount >= 10) {
            switch (cartType) {
                case VIP:
                    discount = discount.add(getCheapestProductPrice(cartDetails));
                    discount = discount.add(BigDecimal.valueOf(500));
                    break;
                case COMMON:
                    discount = discount.add(BigDecimal.valueOf(100));
                    break;
                case SPECIAL_DATE:
                    discount = discount.add(BigDecimal.valueOf(300));
                    break;
                default:
                    break;
            }
        }

        if (cartType.equals(EnumCart.COMMON) && totalPrice.compareTo(BigDecimal.valueOf(10000)) > 0) {
            if(!user.getVoucher()) user.setVoucher(true);
            System.out.println("Pasa a ser vip en la siguiente compra.");
        }

        return discount;
    }

    private BigDecimal getCheapestProductPrice(List<CartDetail> cartDetail) {
        BigDecimal productPrice = cartDetail.get(0).getProduct().getPrice();
        for(CartDetail cartDetail1 : cartDetail){
            if(cartDetail1.getProduct().getPrice().compareTo(productPrice) < 0){
                productPrice = cartDetail1.getProduct().getPrice();
            }
        }
        return productPrice;
    }

    public ResponseEntity<?> getPurchaseHistory() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName();

            Users user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
            Long userID = user.getId();

            List<Cart> carts = cartRepository.findByUserId(userID);
            List<CartDTO> cartDTOs = carts.stream()
                    .map(cartMapper::toDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(cartDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
