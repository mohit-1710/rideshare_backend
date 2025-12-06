package com.ridehub.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class TripResponseDto {

    private String id;
    private String accountId;
    private String assignedDriverId;
    private String pickupAddress;
    private String destinationAddress;
    private String status;
    private Date createdAt;
}
