package com.ridehub.backend.controller;

import com.ridehub.backend.dto.BookTripRequest;
import com.ridehub.backend.model.TripRequest;
import com.ridehub.backend.repository.AccountRepository;
import com.ridehub.backend.service.TripManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TripController {

    @Autowired
    private TripManagementService tripService;

    @Autowired
    private AccountRepository accountRepository;

    private String getCurrentAccountId() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return accountRepository.findByUsername(username)
                .orElseThrow()
                .getId();
    }

    // USER - Create Ride
    @PostMapping("/rides")
    public TripRequest bookTrip(@Valid @RequestBody BookTripRequest request) {
        return tripService.bookTrip(request, getCurrentAccountId());
    }

    // USER/DRIVER - Complete Ride
    @PostMapping("/rides/{rideId}/complete")
    public TripRequest finalizeTrip(@PathVariable String rideId) {
        return tripService.finalizeTrip(rideId);
    }

    // USER - View Own Rides
    @GetMapping("/user/rides")
    public List<TripRequest> getAccountTrips() {
        return tripService.getAccountTrips(getCurrentAccountId());
    }
}
