package com.msvc.hotel.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "hotel")
public class Hotel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "informacion")
    private String informacion;


}
