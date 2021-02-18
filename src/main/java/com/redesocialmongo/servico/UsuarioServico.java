package com.redesocialmongo.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redesocialmongo.dominio.Usuario;
import com.redesocialmongo.dto.UsuarioDTO;
import com.redesocialmongo.repositorio.UsuarioRepositorio;
import com.redesocialmongo.servico.excecao.ObjetoNaoEncontradoExcecao;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	public List<Usuario> buscarTodos() {
		return repositorio.findAll();
	}
	
	public Usuario buscarPorId(String id) {
		Optional<Usuario> usuario = repositorio.findById(id);
		return usuario.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado"));
	}
	
	public Usuario inserir(Usuario usuario) {
		return repositorio.insert(usuario);
	}
	
	public void deletar(String id) {
		buscarPorId(id);
		repositorio.deleteById(id);
	}
	
	public Usuario atualizar(Usuario usuario) {
		Optional<Usuario> novoUsuario = repositorio.findById(usuario.getId());
		atualizarDados(novoUsuario.get(), usuario);
		
		return repositorio.save(novoUsuario.get());
	}
	
	private void atualizarDados(Usuario novoUsuario, Usuario usuario) {
		
		novoUsuario.setNome(usuario.getNome());
		novoUsuario.setEmail(usuario.getEmail());
	}

	public Usuario deDTO(UsuarioDTO usuarioDto) {
		return new Usuario(usuarioDto.getId(), usuarioDto.getNome(), usuarioDto.getEmail());
	}
}
