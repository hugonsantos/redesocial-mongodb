package com.redesocialmongo.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.redesocialmongo.dominio.Post;

@Repository
public interface PostRepositorio extends MongoRepository<Post, String> {

	@Query("{ 'titulo': { $regex: ?0, $options: 'i' } }")
	List<Post> buscarTitulo(String texto);
	
	List<Post> findByTituloContainingIgnoreCase(String texto);
	
	@Query("{ $and: [ { data: {$gte: ?1} }, { data: { $lte: ?2} }, { $or: [ { 'titulo': { $regex: ?0, $options: 'i' } }, { 'conteudo': { $regex: ?0, $options: 'i' } }, { 'comentarios.texto': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> buscarTudo(String texto, Date dataMin, Date dataMax);
}
