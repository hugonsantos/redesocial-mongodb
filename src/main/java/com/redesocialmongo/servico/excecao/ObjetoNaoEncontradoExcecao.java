package com.redesocialmongo.servico.excecao;

public class ObjetoNaoEncontradoExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoExcecao(String mensagem) {
		super(mensagem);
	}
}
