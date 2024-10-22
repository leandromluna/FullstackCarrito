package com.example.carrito.Domain.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "vip_history")
public class VipHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_vip")
    private Date startVip;

    @Column(name = "end_vip")
    private Date endVip;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
