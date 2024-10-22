package com.example.carrito.Repository;

import com.example.carrito.Domain.Model.SpecialDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SpecialDateRepository extends JpaRepository<SpecialDate, Long> {
//    @Query("SELECT sd FROM SpecialDate sd WHERE sd.startDate <= :date AND sd.endDate >= :date")
    @Query("SELECT sd FROM SpecialDate sd WHERE " +
            "(TO_CHAR(sd.startDate, 'MM-dd') <= TO_CHAR(:date, 'MM-dd') AND " +
            "TO_CHAR(sd.endDate, 'MM-dd') >= TO_CHAR(:date, 'MM-dd')) OR " +
            "(TO_CHAR(sd.startDate, 'MM-dd') > TO_CHAR(sd.endDate, 'MM-dd') AND " +
            "(TO_CHAR(:date, 'MM-dd') >= TO_CHAR(sd.startDate, 'MM-dd') OR " +
            "TO_CHAR(:date, 'MM-dd') <= TO_CHAR(sd.endDate, 'MM-dd')))")
    List<SpecialDate> findByDateInRange(Date date);
}