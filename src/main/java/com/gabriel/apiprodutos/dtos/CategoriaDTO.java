package com.gabriel.apiprodutos.dtos;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CategoriaDTO {

	private int id;
	private String descricao;
	private LocalDate dataCadastro;
	private List<ProdutoDTO> produtos;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return this.descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataCadastro() {
		return this.dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public void setProdutos(List<ProdutoDTO> produtos) {
		this.produtos = produtos;
	}
	public List<ProdutoDTO> getProdutos() {
		return Collections.unmodifiableList(this.produtos);
	}
}
