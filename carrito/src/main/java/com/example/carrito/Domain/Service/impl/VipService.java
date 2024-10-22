package com.example.carrito.Domain.Service.impl;

import com.example.carrito.DTO.VipHistoryDTO;
import com.example.carrito.Domain.Model.EnumROL;
import com.example.carrito.Domain.Model.Users;
import com.example.carrito.Domain.Model.VipHistory;
import com.example.carrito.Domain.Service.iVipService;
import com.example.carrito.Mapper.VipHistoryMapper;
import com.example.carrito.Repository.UserRepository;
import com.example.carrito.Repository.VipHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VipService implements iVipService {

    @Autowired
    private VipHistoryRepository vipHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VipHistoryMapper vipHistoryMapper;

    public void updateVipStatus(Date currentDate) {
        List<VipHistory> vipHistories = vipHistoryRepository.findAll();

        for (VipHistory vipHistory : vipHistories) {
            if(vipHistory.getEndVip() != null) continue;
            Users user = vipHistory.getUser();
            Date startVipDate = vipHistory.getStartVip();
            Date lastPurchaseDate = user.getLastBuy();
            Date endVipDate = null;

            if (lastPurchaseDate == null) {
                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(startVipDate);
                Calendar calendarCurrent = Calendar.getInstance();
                calendarCurrent.setTime(currentDate);

                calendarStart.add(Calendar.MONTH, 1);
                if (calendarCurrent.after(calendarStart)) {
                    endVipDate = new Date(calendarStart.getTimeInMillis());
                }
            } else {
                Calendar calendarLastBuy = Calendar.getInstance();
                calendarLastBuy.setTime(lastPurchaseDate);
                Calendar calendarCurrent = Calendar.getInstance();
                calendarCurrent.setTime(currentDate);
                calendarLastBuy.add(Calendar.MONTH, 1);
                if (calendarCurrent.after(calendarLastBuy)) {
                    endVipDate = new Date(calendarLastBuy.getTimeInMillis());
                }
            }

            if (endVipDate != null) {
                vipHistory.setEndVip(endVipDate);
                user.setRol(EnumROL.USER);
                userRepository.save(user);
            }
        }

        vipHistoryRepository.saveAll(vipHistories);
    }




    @Override
    public List<VipHistoryDTO> getVipHistory() {
        return vipHistoryRepository.findAll()
                .stream()
                .map(vipHistoryMapper::toDto)
                .collect(Collectors.toList());

    }
}

