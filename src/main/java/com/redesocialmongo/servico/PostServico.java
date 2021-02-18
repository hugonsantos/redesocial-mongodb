package com.redesocialmongo.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redesocialmongo.dominio.Post;
import com.redesocialmongo.repositorio.PostRepositorio;
import com.redesocialmongo.servico.excecao.ObjetoNaoEncontradoExcecao;

@Service
public class PostServico {

	@Autowired
	private PostRepositorio repositorio;
	
	public List<Post> buscarTodos() {
		return repositorio.findAll();
	}
	
	public Post buscarPorId(String id) {
		Optional<Post> post = repositorio.findById(id);
		return post.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado"));
	}
	
	public Post inserir(Post post) {
		return repositorio.insert(post);
	}
	
	public void deletar(String id) {
		buscarPorId(id);
		repositorio.deleteById(id);
	}
	
	public Post atualizar(Post post) {
		Optional<Post> novoPost = repositorio.findById(post.getId());
		atualizarDados(novoPost.get(), post);
		
		return repositorio.save(novoPost.get());
	}
	
	private void atualizarDados(Post novoPost, Post post) {
		
		novoPost.setTitulo(post.getTitulo());
		novoPost.setConteudo(post.getConteudo());
	}
}
