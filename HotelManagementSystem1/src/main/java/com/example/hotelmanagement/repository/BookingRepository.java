package com.example.hotelmanagement.repository;


import com.example.hotelmanagement.entity.Booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Additional custom queries can be defined here if needed
	   List<Booking> findByHotelId(Long hotelId);
}
