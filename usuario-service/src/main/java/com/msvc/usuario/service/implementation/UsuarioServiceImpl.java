package com.msvc.usuario.service.implementation;

import com.msvc.usuario.entities.Hotel;
import com.msvc.usuario.entities.Qualification;
import com.msvc.usuario.entities.Usuario;
import com.msvc.usuario.exceptions.ResourceNotFoundException;
import com.msvc.usuario.external.services.HotelService;
import com.msvc.usuario.repository.UsuarioRepository;
import com.msvc.usuario.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HotelService hotelService;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        String randomUsuarioId = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUsuarioId);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuario(String usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with ID: " +  usuarioId));

        Qualification[] userQualifications = restTemplate.getForObject("http://QUALIFICATION-SERVICE/qualifications/usuarios/"+usuario.getUsuarioId(), Qualification[].class);
        logger.info("{}",userQualifications);

        List<Qualification> qualifications = Arrays.stream(userQualifications).collect(Collectors.toList());

        List<Qualification> listQualifications = qualifications.stream().map(qualification -> {
            System.out.println(qualification.getHotelId());
            //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hoteles/"+qualification.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(qualification.getHotelId());
            //logger.info("Response with status code : {}",forEntity.getStatusCode());

            qualification.setHotel(hotel);
            return qualification;
        }).collect(Collectors.toList());

        usuario.setQualifications(listQualifications);

        return usuario;
    }
}

/**
 * MISMO METODO USANDO REST TEMPLATE
 * @Override
 * public Usuario getUsuario(String usuarioId) {
 *         Usuario usuario = usuarioRepository.findById(usuarioId)
 *                 .orElseThrow(()-> new ResourceNotFoundException("User not found with ID: " +  usuarioId));
 *
 *         Qualification[] userQualifications = restTemplate.getForObject("http://QUALIFICATION-SERVICE/qualifications/usuarios/"+usuario.getUsuarioId(), Qualification[].class);
 *         logger.info("{}",userQualifications);
 *
 *         List<Qualification> qualifications = Arrays.stream(userQualifications).collect(Collectors.toList());
 *
 *         List<Qualification> listQualifications = qualifications.stream().map(qualification -> {
 *             System.out.println(qualification.getHotelId());
 *             ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hoteles/"+qualification.getHotelId(), Hotel.class);
 *             Hotel hotel = forEntity.getBody();
 *             logger.info("Response with status code : {}",forEntity.getStatusCode());
 *
 *             qualification.setHotel(hotel);
 *             return qualification;
 *         }).collect(Collectors.toList());
 *
 *         usuario.setQualifications(listQualifications);
 *
 *         return usuario;
 *     }
 * }
 */
