package com.ridehub.backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "trip_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripRequest {

    @Id
    private String id;

    private String accountId;      // Passenger (formerly userId)
    private String assignedDriverId;    // Driver (formerly driverId)

    private String pickupAddress; // formerly pickupLocation
    private String destinationAddress; // formerly dropLocation

    // REQUESTED / ACCEPTED / COMPLETED
    private String status;

    private Date createdAt;
}
