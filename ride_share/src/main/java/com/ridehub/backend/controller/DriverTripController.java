package com.ridehub.backend.controller;

import com.ridehub.backend.model.TripRequest;
import com.ridehub.backend.repository.AccountRepository;
import com.ridehub.backend.service.TripManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverTripController {

    @Autowired
    private TripManagementService tripService;

    @Autowired
    private AccountRepository accountRepository;

    private String getCurrentDriverId() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return accountRepository.findByUsername(username)
                .orElseThrow()
                .getId();
    }

    // DRIVER - View Pending Requests
    @GetMapping("/rides/requests")
    public List<TripRequest> fetchPendingRequests() {
        return tripService.fetchPendingRequests();
    }

    // DRIVER - Accept Ride
    @PostMapping("/rides/{rideId}/accept")
    public TripRequest confirmTrip(@PathVariable String rideId) {
        return tripService.confirmTrip(rideId, getCurrentDriverId());
    }
}
