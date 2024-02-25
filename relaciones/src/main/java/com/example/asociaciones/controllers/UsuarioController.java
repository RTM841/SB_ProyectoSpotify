package com.example.asociaciones.controllers;

import com.example.asociaciones.entity.Usuario;
import com.example.asociaciones.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @GetMapping
    public List<Usuario> list(){
        return usuarioService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario, BindingResult result){
        return create(usuario, result);
    }



    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo "+ err.getField()+ " "+ err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        UserDetails userDetails;
        try {
            userDetails = usuarioService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        // Aquí puedes devolver los detalles del usuario como una respuesta
        // Por ejemplo, puedes devolver solo el nombre de usuario por ahora
        Map<String, String> userResponse = new HashMap<>();
        userResponse.put("username", userDetails.getUsername());
        return ResponseEntity.ok(userResponse);
    }



}
