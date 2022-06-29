package com.gabriel.apiprodutos.utils;

public class RetornoRequisicao<T> {

	private String mensagem;
	private T conteudo;
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getMensagem() {
		return this.mensagem;
	}
	public void setConteudo(T conteudo) {
		this.conteudo = conteudo;
	}
	public T getConteudo() {
		return this.conteudo;
	}
}
