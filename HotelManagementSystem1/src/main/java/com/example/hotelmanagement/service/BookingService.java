package com.example.hotelmanagement.service;


import com.example.hotelmanagement.entity.Booking;
import com.example.hotelmanagement.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // Create a new booking
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Retrieve a list of all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Retrieve a booking by its ID
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    // Update an existing booking
    public Booking updateBooking(Long id, Booking updatedBooking) {
        return bookingRepository.findById(id).map(existingBooking -> {
            existingBooking.setUserName(updatedBooking.getUserName());
            existingBooking.setEmail(updatedBooking.getEmail());
            existingBooking.setCheckInDate(updatedBooking.getCheckInDate());
            existingBooking.setCheckOutDate(updatedBooking.getCheckOutDate());
            existingBooking.setContact(updatedBooking.getContact());
            existingBooking.setTotalRooms(updatedBooking.getTotalRooms());
            return bookingRepository.save(existingBooking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
    }

    // Delete a booking by its ID
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

	public Booking saveBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepository.save(booking);
	}
	
	public List<Booking> getBookingsByHotelId(Long hotelId) {
	    return bookingRepository.findByHotelId(hotelId);
	}
	
}
