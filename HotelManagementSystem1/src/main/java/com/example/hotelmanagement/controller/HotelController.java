package com.example.hotelmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hotelmanagement.entity.Booking;
import com.example.hotelmanagement.entity.Hotel;
import com.example.hotelmanagement.service.BookingService;
import com.example.hotelmanagement.service.HotelService;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    private final BookingService bookingService;
    private final HotelService hotelService;

    public HotelController(BookingService bookingService, HotelService hotelService) {
        this.bookingService = bookingService;
        this.hotelService = hotelService;
    }

    @PostMapping("/{id}/confirm")
    public String confirmBooking(@PathVariable Long id, @ModelAttribute Booking booking, Model model) {
        
    	 Hotel hotel = hotelService.getHotelById(id);
    	    
    	    // Set the hotel in the booking
    	    booking.setHotel(hotel);
    	    // Save the booking details
        bookingService.saveBooking(booking);
        
        // Pass the booking details and hotel details to the confirmation page
        model.addAttribute("booking", booking);
        model.addAttribute("hotel", hotelService.getHotelById(id)); // Get hotel details for confirmation
        
        return "/user/confirm"; // The name of your confirmation template
    }
}
