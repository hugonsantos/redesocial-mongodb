package com.redesocialmongo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.redesocialmongo.dominio.Post;

@Repository
public interface PostRepositorio extends MongoRepository<Post, String> {

}
