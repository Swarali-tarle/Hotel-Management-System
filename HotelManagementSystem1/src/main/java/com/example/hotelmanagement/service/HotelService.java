package com.example.hotelmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotelmanagement.entity.Hotel;
import com.example.hotelmanagement.repository.HotelRepository;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        // Fetch the existing hotel from the database by ID
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + id));
        
        // Update the fields
        existingHotel.setName(updatedHotel.getName());
        existingHotel.setCity(updatedHotel.getCity());
        existingHotel.setAddress(updatedHotel.getAddress());
        existingHotel.setPrice(updatedHotel.getPrice());
        existingHotel.setRating(updatedHotel.getRating());
        existingHotel.setImage(updatedHotel.getImage());

        // Save the updated hotel
        return hotelRepository.save(existingHotel);
    }
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
