package com.msvc.usuario.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Qualification {

    private String qualificationId;

    private String userId;

    private String hotelId;

    private int calification;

    private String observations;

    private Hotel hotel;

}
