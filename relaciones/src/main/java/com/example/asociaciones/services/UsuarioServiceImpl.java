package com.example.asociaciones.services;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.entity.Rol;
import com.example.asociaciones.entity.Usuario;
import com.example.asociaciones.repositories.RoleRepository;
import com.example.asociaciones.repositories.UsuarioRepository;
import io.jsonwebtoken.security.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        Optional<Rol> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List <Rol> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        if(usuario.isAdmin()){
            Optional<Rol> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        usuario.setRoles(roles);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> userOptional = usuarioRepository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException((String.format("Username %s no existe", username)));
        }
        Usuario usuario = userOptional.orElseThrow();

        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnabled(),
                true,
                true,
                true,
                authorities);

    }

    @Override
    @Transactional
    public Optional<Usuario> update(Long id, Usuario usuario) {
        Optional <Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuarioDB = usuarioOptional.orElseThrow();
            usuarioDB.setUsername(usuario.getUsername());
            usuarioDB.setPassword(usuario.getPassword());
            return Optional.of(usuarioRepository.save(usuarioDB));
        }
        return usuarioOptional;
    }

    @Override
    public Optional<Usuario> delete(Long id) {
        Optional <Usuario> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.ifPresent( usuarioDb -> usuarioRepository.delete(usuarioDb));
        return usuarioOptional;
    }
}
