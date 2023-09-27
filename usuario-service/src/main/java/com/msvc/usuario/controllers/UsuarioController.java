package com.msvc.usuario.controllers;

import com.msvc.usuario.entities.Usuario;
import com.msvc.usuario.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuarioRequest) {
        Usuario usuario = usuarioService.saveUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    int cantidadReintentos = 1 ;

    @GetMapping("/{usuarioId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String usuarioId) {
        log.info("List a single user : UsuarioController");
        log.info("Number of attempts : {}", cantidadReintentos);
        cantidadReintentos++;
        Usuario usuario = usuarioService.getUsuario(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    public ResponseEntity<Usuario> ratingHotelFallback(String usuarioId, Exception exception) {

        log.info("The backup runs because the service is down : ",exception.getMessage());
        Usuario usuario = Usuario.builder()
                .email("mike@gmail.com")
                .nombre("mike")
                .informacion("This user is created by default when the service goes down")
                .usuarioId("1234")
                .build();

        return new ResponseEntity<>(usuario,HttpStatus.OK);

    }
}
