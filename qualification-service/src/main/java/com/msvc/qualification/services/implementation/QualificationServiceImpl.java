package com.msvc.qualification.services.implementation;

import com.msvc.qualification.entities.Qualification;
import com.msvc.qualification.repository.QualificationRepository;
import com.msvc.qualification.services.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;
    @Override
    public Qualification create(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Override
    public List<Qualification> getQualifications() {
        return qualificationRepository.findAll();
    }

    @Override
    public List<Qualification> getQualificationsByUserId(String userId) {
        return qualificationRepository.findByUserId(userId);
    }

    @Override
    public List<Qualification> getQualificationsByHotelId(String hotelId) {
        return qualificationRepository.findByHotelId(hotelId);
    }
}
