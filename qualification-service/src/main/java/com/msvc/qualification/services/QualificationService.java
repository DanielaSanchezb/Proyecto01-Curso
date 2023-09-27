package com.msvc.qualification.services;

import com.msvc.qualification.entities.Qualification;

import java.util.List;

public interface QualificationService {

    Qualification create (Qualification qualification);

    List<Qualification> getQualifications();

    List<Qualification> getQualificationsByUserId(String userId);

    List<Qualification> getQualificationsByHotelId(String hotel);
}
