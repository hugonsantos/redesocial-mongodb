package com.redesocialmongo.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.redesocialmongo.dominio.Post;
import com.redesocialmongo.dominio.Usuario;
import com.redesocialmongo.dto.UsuarioDTO;
import com.redesocialmongo.servico.UsuarioServico;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRecurso {

	@Autowired
	private UsuarioServico servico;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
		
		List<UsuarioDTO> lista = servico.buscarTodos().stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable String id) {
		
		Usuario u = servico.buscarPorId(id);
		return ResponseEntity.ok().body(new UsuarioDTO(u));
	}
	
	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody UsuarioDTO usuarioDto) {
		
		Usuario usuario = servico.deDTO(usuarioDto);
		usuario = servico.inserir(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioDTO> deletar(@PathVariable String id) {
		
		servico.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody UsuarioDTO usuarioDto, @PathVariable String id) {
		
		Usuario usuario = servico.deDTO(usuarioDto);
		usuario.setId(id);
		usuario = servico.atualizar(usuario);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> buscarPosts(@PathVariable String id) {
		
		Usuario u = servico.buscarPorId(id);
		return ResponseEntity.ok().body(u.getPosts());
	}
}
