package com.example.carrito.Domain.Service.impl;

import com.example.carrito.Domain.Model.SpecialDate;
import com.example.carrito.Domain.Service.iSpecialDateService;
import com.example.carrito.Domain.Service.iVipService;
import com.example.carrito.Repository.SpecialDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SpecialDateService implements iSpecialDateService {

    @Autowired
    private SpecialDateRepository specialDateRepository;

    @Autowired
    private iVipService vipService;

    public boolean isSpecialDate(Date date) {
        List<SpecialDate> specialDates = specialDateRepository.findByDateInRange(date);

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(Calendar.HOUR_OF_DAY, 0);
        nowCalendar.set(Calendar.MINUTE, 0);
        nowCalendar.set(Calendar.SECOND, 0);
        nowCalendar.set(Calendar.MILLISECOND, 0);
        Date dateNow = nowCalendar.getTime();

        if(!date.equals(dateNow)) vipService.updateVipStatus(date);
        return !specialDates.isEmpty();
    }
}
