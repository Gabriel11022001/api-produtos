package com.gabriel.apiprodutos.models;

import java.time.LocalDate;

public class Produto {

	private int id;
	private String nome;
	private String descricaoResumida;
	private String descricaoCompleta;
	private double precoVenda;
	private LocalDate dataCadastro;
	private boolean emDestaque;
	private int quantidadeUnidadesEmEstoque;
	private int categoriaId;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricaoResumida() {
		return this.descricaoResumida;
	}
	public void setDescricaoResumida(String descricaoResumida) {
		this.descricaoResumida = descricaoResumida;
	}
	public String getDescricaoCompleta() {
		return this.descricaoCompleta;
	}
	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}
	public double getPrecoVenda() {
		return this.precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public LocalDate getDataCadastro() {
		return this.dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public boolean isEmDestaque() {
		return this.emDestaque;
	}
	public void setEmDestaque(boolean emDestaque) {
		this.emDestaque = emDestaque;
	}
	public int getCategoriaId() {
		return this.categoriaId;
	}
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	public int getQuantidadeUnidadesEmEstoque() {
		return this.quantidadeUnidadesEmEstoque;
	}
	public void setQuantidadeUnidadesEmEstoque(int quantidadeUnidadesEmEstoque) {
		this.quantidadeUnidadesEmEstoque = quantidadeUnidadesEmEstoque;
	}
}
