package com.redesocialmongo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.redesocialmongo.dominio.Usuario;

@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {

}
