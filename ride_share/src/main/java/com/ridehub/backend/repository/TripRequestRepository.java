package com.ridehub.backend.repository;

import com.ridehub.backend.model.TripRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRequestRepository extends MongoRepository<TripRequest, String> {
    List<TripRequest> findAllByStatus(String status);
    List<TripRequest> findAllByAccountId(String accountId);
}
