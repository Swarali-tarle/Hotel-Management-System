package com.example.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotelmanagement.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

