package com.vgmsistemas.vgminterface.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vgmsistemas.vgminterface.entity.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long>  {
    public Optional<Usuario> findByUsuario(String username);
}