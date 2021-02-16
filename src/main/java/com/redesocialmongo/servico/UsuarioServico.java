package com.redesocialmongo.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redesocialmongo.dominio.Usuario;
import com.redesocialmongo.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServico {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	public List<Usuario> buscarTodos() {
		
		return repositorio.findAll();
	}
}
