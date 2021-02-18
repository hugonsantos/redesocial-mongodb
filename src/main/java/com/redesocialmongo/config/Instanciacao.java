package com.redesocialmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.redesocialmongo.dominio.Post;
import com.redesocialmongo.dominio.Usuario;
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
		
		Post post1 = new Post(null, sdf.parse("22/03/2021"), "Partiu viagem!", "Vou viajar para São Paulo, abraços!", maria);
		Post post2 = new Post(null, sdf.parse("22/03/2021"), "Bom dia", "Acordei feliz hoje!", maria);
		
		usuarioRepositorio.saveAll(Arrays.asList(maria, alex, bob));
		postRepositorio.saveAll(Arrays.asList(post1, post2));
	}
}
