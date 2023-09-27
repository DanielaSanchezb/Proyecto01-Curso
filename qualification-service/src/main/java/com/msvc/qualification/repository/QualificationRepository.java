package com.msvc.qualification.repository;

import com.msvc.qualification.entities.Qualification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QualificationRepository extends MongoRepository<Qualification, Long> {

    List<Qualification> findByUserId(String UserId);

    List<Qualification> findByHotelId(String hotelId);

}
