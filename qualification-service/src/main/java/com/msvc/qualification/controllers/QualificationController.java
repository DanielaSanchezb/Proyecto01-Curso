package com.msvc.qualification.controllers;

import com.msvc.qualification.entities.Qualification;
import com.msvc.qualification.services.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @PostMapping
    public ResponseEntity<Qualification> saveQualification(@RequestBody Qualification qualification){
        return ResponseEntity.status(HttpStatus.CREATED).body(qualificationService.create(qualification));
    }

    @GetMapping
    public ResponseEntity<List<Qualification>> listQualifcation(){
        return ResponseEntity.ok(qualificationService.getQualifications());
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<Qualification>> listQualificationsByUserId(@PathVariable String usuarioId){
        return ResponseEntity.ok(qualificationService.getQualificationsByUserId(usuarioId));
    }

    @GetMapping("/hoteles/{hotelId}")
    public ResponseEntity<List<Qualification>> listQualificationsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(qualificationService.getQualificationsByHotelId(hotelId));
    }
}
