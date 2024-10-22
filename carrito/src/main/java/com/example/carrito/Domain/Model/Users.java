package com.example.carrito.Domain.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "last_buy")
    private Date lastBuy;

    @Column(name = "voucher")
    private Boolean voucher;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('USER', 'ADMIN', 'VIP') DEFAULT 'USER'", nullable = true)
    private EnumROL rol;

    @PrePersist
    public void prePersist() {
        if (rol == null) {
            rol = EnumROL.USER;
        }
    }

    public Date getLastBuy() {
        return lastBuy;
    }

    public void setLastBuy(Date lastBuy) {
        this.lastBuy = lastBuy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnumROL getRol() {
        return rol;
    }

    public void setRol(EnumROL rol) {
        this.rol = rol;
    }

    public Boolean getVoucher() {
        return voucher;
    }

    public void setVoucher(Boolean voucher) {
        this.voucher = voucher;
    }
}
