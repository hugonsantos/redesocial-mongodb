package com.redesocialmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.redesocialmongo.dominio.Post;
import com.redesocialmongo.dominio.Usuario;
import com.redesocialmongo.dto.AutorDTO;
import com.redesocialmongo.dto.ComentarioDTO;
import com.redesocialmongo.repositorio.PostRepositorio;
import com.redesocialmongo.repositorio.UsuarioRepositorio;

@Configuration
public class Instanciacao implements CommandLineRunner {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private PostRepositorio postRepositorio;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		usuarioRepositorio.deleteAll();
		postRepositorio.deleteAll();
		
		Usuario maria = new Usuario(null, "Maria Brown", "maria@gmail.com");
		Usuario alex = new Usuario(null, "Alex Green", "alex@gmail.com");
		Usuario bob = new Usuario(null, "Bob Grey", "bob@gmail.com");
		
		usuarioRepositorio.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("22/03/2020"), "Partiu viagem!", "Vou viajar para São Paulo, abraços!", new AutorDTO(maria));
		Post post2 = new Post(null, sdf.parse("10/04/2020"), "Bom dia", "Acordei feliz hoje!", new AutorDTO(maria));
		
		ComentarioDTO comentario1 = new ComentarioDTO("Boa viagem", sdf.parse("22/03/2020"), new AutorDTO(alex));
		ComentarioDTO comentario2 = new ComentarioDTO("Aproveite", sdf.parse("22/03/2020"), new AutorDTO(bob));
		ComentarioDTO comentario3 = new ComentarioDTO("Tenha um ótimo dia!", sdf.parse("10/04/2020"), new AutorDTO(alex));
		
		post1.getComentarios().addAll(Arrays.asList(comentario1, comentario2));
		post2.getComentarios().add(comentario3);
		
		postRepositorio.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		usuarioRepositorio.save(maria);
	}
}
