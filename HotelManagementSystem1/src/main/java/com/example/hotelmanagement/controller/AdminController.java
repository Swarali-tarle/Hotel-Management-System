package com.example.hotelmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotelmanagement.entity.Booking;
import com.example.hotelmanagement.entity.Hotel;
import com.example.hotelmanagement.service.BookingService;
import com.example.hotelmanagement.service.HotelService;

@Controller
//@RequestMapping("/admin")
public class AdminController {
    private final HotelService hotelService;
    private final BookingService bookingService;

    public AdminController(HotelService hotelService, BookingService bookingService) {
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }
    
    @GetMapping
    public String dashboard() {
        return "admin/dashboard";
    }
    
    
    
    

    @GetMapping("/admin/hotels")
    public String listHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "admin/hotels";
    }

    @GetMapping("admin/hotels/add")
    public String showAddHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "admin/add-hotel";
    }

    @PostMapping("admin/hotels/add")
    public String addHotel(@ModelAttribute Hotel hotel) {
        hotelService.addHotel(hotel);
        return "redirect:/admin/hotels";
    }

    @GetMapping("admin/hotels/edit/{id}")
    public String showEditHotelForm(@PathVariable Long id, Model model) {
        model.addAttribute("hotel", hotelService.getHotelById(id));
        return "admin/edit-hotel";
    }

    @PostMapping("admin/hotels/edit/{id}")
    public String updateHotel(@PathVariable Long id, @ModelAttribute Hotel hotel) {
        hotelService.updateHotel(id, hotel);  // Pass the ID and updated hotel object
        return "redirect:/admin/hotels";
    }

    @GetMapping("admin/hotels/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return "redirect:/admin/hotels";
    }
    
//    @GetMapping("admin/hotel-list/manage-bookings")
//    public String manageBookings(Model model) {
//   	 List<Booking> bookings = bookingService.getAllBookings();
//   	    model.addAttribute("bookings", bookings);
//        return "admin/manage-bookings";    
//        }
    
    @GetMapping("admin/hotel-list")
    public String showHotelList(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "admin/hotel-list";
    }
    
    @GetMapping("admin/hotel-list/{id}/bookings")
    public String viewHotelBookings(@PathVariable("id") Long hotelId, Model model) {
        List<Booking> bookings = bookingService.getBookingsByHotelId(hotelId);
        model.addAttribute("bookings", bookings);
        System.out.println("Bookings for Hotel ID " + hotelId + ": " + bookings);
        return "admin/manage-bookings";
    }
    
    @GetMapping("admin/hotel-list/{hotelId}")
    public String showLoginForBookings(@PathVariable Long hotelId, Model model) {
        model.addAttribute("hotelId", hotelId); // Pass the hotel ID to the model
        System.out.println("hello");
        return "admin/manage-bookings";
    }

}
