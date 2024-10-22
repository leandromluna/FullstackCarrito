package com.example.carrito.Controller;


import com.example.carrito.Domain.Service.iSpecialDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/specialdates")
public class SpecialDateController {

    @Autowired
    private iSpecialDateService specialDateService;


    @PostMapping("/validate")
    public ResponseEntity<String> validateSpecialDate(@RequestBody Map<String, String> payload) {
        String dateString = payload.get("date");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        boolean isSpecial = specialDateService.isSpecialDate(date);

        return ResponseEntity.ok(isSpecial ? "It's a special date." : "It's not a special date.");
    }

}