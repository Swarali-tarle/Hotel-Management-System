package com.example.hotelmanagement.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.hotelmanagement.entity.Booking;
import com.example.hotelmanagement.entity.Hotel;
import com.example.hotelmanagement.service.BookingService;
import com.example.hotelmanagement.service.HotelService;

@Controller
public class UserController {
    private final HotelService hotelService;
    private final BookingService bookingService;


    public UserController(HotelService hotelService, BookingService bookingService) {
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }

    // Route for users to view the list of available hotels
    @GetMapping("/hotels")
    public String viewHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "user/hotel-list"; // Render the "user/hotels.html" template
    }
    
    @GetMapping("/hotels/{id}/book")
    public String showBookingForm(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("booking", new Booking()); // Create a new booking object for form
        return "user/booking";
    }
    
//    @PostMapping("/hotels/{id}/book")
//    public String bookHotel(@PathVariable Long id, @ModelAttribute Booking booking) {
//        Hotel hotel = hotelService.getHotelById(id);
//        booking.setHotel(hotel);
//        bookingService.saveBooking(booking); // Save the booking
//        return "redirect:/confirm"; // Redirect to hotels list after booking
//    }
    
    @GetMapping("/index")
    public String landing() {
        return "/user/index";
    }
}
