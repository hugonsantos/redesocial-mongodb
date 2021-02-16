package com.redesocialmongo.recursos;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redesocialmongo.dominio.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioRecurso {

	@GetMapping
	public ResponseEntity<List<Usuario>> buscarTodos() {
		
		Usuario u1 = new Usuario("1", "Maria", "maria@gmail.com");
		Usuario u2 = new Usuario("2", "Bob", "bob@gmail.com");
		Usuario u3 = new Usuario("3", "Alex", "alex@gmail.com");
		
		return ResponseEntity.ok().body(Arrays.asList(u1, u2, u3));
	}
}
