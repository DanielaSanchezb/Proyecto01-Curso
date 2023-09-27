package com.msvc.usuario.external.services;

import com.msvc.usuario.entities.Qualification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "QUALIFICATION-SERVICE")
public interface QualificationService {

    @PostMapping
    public ResponseEntity<Qualification> saveQualification(Qualification qualification);

    @PostMapping("/qualifications/{qualificationId}")
    public ResponseEntity<Qualification> updateQualification(@PathVariable String qualificationId,Qualification qualification);

    @DeleteMapping("/qualifications/{qualificationId}")
    public void deleteQualification(@PathVariable String qualificationId);

}
