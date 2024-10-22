package com.example.carrito.Controller;

import com.example.carrito.Domain.Service.iSpecialDateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SpecialDateControllerTest {

    @InjectMocks
    private SpecialDateController specialDateController;

    @Mock
    private iSpecialDateService specialDateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateSpecialDate_ReturnsSpecialDateMessage() {
        String dateString = "15/08/2023";
        Map<String, String> payload = new HashMap<>();
        payload.put("date", dateString);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            boolean isSpecial = true;
            when(specialDateService.isSpecialDate(formatter.parse(dateString))).thenReturn(isSpecial);

            ResponseEntity<String> response = specialDateController.validateSpecialDate(payload);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals("It's a special date.", response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void validateSpecialDate_ReturnsNotSpecialDateMessage() {
        String dateString = "16/08/2023";
        Map<String, String> payload = new HashMap<>();
        payload.put("date", dateString);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            boolean isSpecial = false;
            when(specialDateService.isSpecialDate(formatter.parse(dateString))).thenReturn(isSpecial);

            ResponseEntity<String> response = specialDateController.validateSpecialDate(payload);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals("It's not a special date.", response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
