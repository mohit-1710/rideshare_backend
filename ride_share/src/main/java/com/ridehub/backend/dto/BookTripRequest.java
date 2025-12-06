package com.ridehub.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTripRequest {

    @NotBlank(message = "Pickup address is required")
    private String pickupAddress;

    @NotBlank(message = "Destination address is required")
    private String destinationAddress;
}
