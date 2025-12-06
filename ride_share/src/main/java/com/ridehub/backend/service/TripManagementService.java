package com.ridehub.backend.service;

import com.ridehub.backend.constants.TripStatus;
import com.ridehub.backend.dto.BookTripRequest;
import com.ridehub.backend.exception.InvalidRequestException;
import com.ridehub.backend.exception.ResourceNotFoundException;
import com.ridehub.backend.model.TripRequest;
import com.ridehub.backend.repository.TripRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TripManagementService {

    @Autowired
    private TripRequestRepository tripRepository;

    public TripRequest bookTrip(BookTripRequest request, String accountId) {
        TripRequest trip = TripRequest.builder()
                .accountId(accountId)
                .pickupAddress(request.getPickupAddress())
                .destinationAddress(request.getDestinationAddress())
                .status(TripStatus.REQUESTED)
                .createdAt(new Date())
                .build();

        return tripRepository.save(trip);
    }

    public List<TripRequest> fetchPendingRequests() {
        return tripRepository.findAllByStatus(TripStatus.REQUESTED);
    }

    public TripRequest confirmTrip(String tripId, String driverId) {
        TripRequest trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip request does not exist"));

        if (!TripStatus.REQUESTED.equals(trip.getStatus())) {
            throw new InvalidRequestException("Trip is not available for confirmation");
        }

        trip.setAssignedDriverId(driverId);
        trip.setStatus(TripStatus.ACCEPTED);

        return tripRepository.save(trip);
    }

    public TripRequest finalizeTrip(String tripId) {
        TripRequest trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip request does not exist"));

        if (!TripStatus.ACCEPTED.equals(trip.getStatus())) {
            throw new InvalidRequestException("Trip must be accepted before completion");
        }

        trip.setStatus(TripStatus.COMPLETED);
        return tripRepository.save(trip);
    }

    public List<TripRequest> getAccountTrips(String accountId) {
        return tripRepository.findAllByAccountId(accountId);
    }
}
