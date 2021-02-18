package com.redesocialmongo.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redesocialmongo.dominio.Post;
import com.redesocialmongo.recursos.util.URL;
import com.redesocialmongo.servico.PostServico;

@RestController
@RequestMapping("/posts")
public class PostRecurso {
	
	@Autowired
	private PostServico servico;
	
	@GetMapping
	public ResponseEntity<List<Post>> buscarTodos() {
		return ResponseEntity.ok().body(servico.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> buscarPost(@PathVariable String id) {
		
		Post post = servico.buscarPorId(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping("/buscartitulo")
	public ResponseEntity<List<Post>> buscarTitulo(@RequestParam(value = "texto", defaultValue = "") String texto) {
		
		texto = URL.decodificarParametro(texto);
		
		List<Post> posts = servico.buscarTitulo(texto);
		return ResponseEntity.ok().body(posts);
	}
}
