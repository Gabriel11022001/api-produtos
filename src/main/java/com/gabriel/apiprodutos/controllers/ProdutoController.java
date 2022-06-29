package com.gabriel.apiprodutos.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gabriel.apiprodutos.dtos.ProdutoDTO;
import com.gabriel.apiprodutos.models.Produto;
import com.gabriel.apiprodutos.repositorios.ProdutoRepositorio;
import com.gabriel.apiprodutos.utils.RetornoRequisicao;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProdutoController {

	private Gson gson;
	private ProdutoRepositorio produtoRepositorio;
	
	public ProdutoController() {
		this.gson = new Gson();
		this.produtoRepositorio = new ProdutoRepositorio();
	}
	public void cadastrarProduto(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
		RetornoRequisicao<ProdutoDTO> retornoRequisicao = new RetornoRequisicao<ProdutoDTO>();
		try {
			BufferedReader bf = requisicao.getReader();
			String conteudoJson = "";
			while (bf.ready()) {
				conteudoJson += bf.readLine();
			}
			Produto produto = this.gson.fromJson(conteudoJson, Produto.class);
			if (produto.getNome() == null) {
				throw new Exception("O nome do produto não deve estar apontando para null!");
			}
			if (produto.getNome().isEmpty()) {
				throw new Exception("Informe o nome do produto!");
			}
			if (produto.getDescricaoResumida() == null) {
				throw new Exception("A descrição resumida do produto não deve estar apontando para null!");
			}
			if (produto.getDescricaoResumida().isEmpty()) {
				throw new Exception("Informe a descrição resumida do produto!");
			}
			if (produto.getDescricaoCompleta() == null) {
				throw new Exception("A descrição completa do produto não deve estar apontando para null!");
			}
			if (produto.getDescricaoCompleta().isEmpty()) {
				throw new Exception("Informe a descrição completa do produto!");
			}
			if (produto.getPrecoVenda() <= 0) {
				throw new Exception("O preço de venda do produto não deve ser menor ou igual a 0!"); 
			}
			if (produto.getQuantidadeUnidadesEmEstoque() < 0) {
				throw new Exception("A quantidade de unidades do produto em estoque não deve ser menor que 0!");
			}
			if (produto.getCategoriaId() <= 0) {
				throw new Exception("Id da categoria inválido, o id da categoria deve ser um valor maior que zero e inteiro!");
			}
			produto.setDataCadastro(LocalDate.now());
			ProdutoDTO produtoDTO = this.produtoRepositorio.salvar(produto);
			retornoRequisicao.setMensagem("Produto cadastrado com sucesso!");
			retornoRequisicao.setConteudo(produtoDTO);
		} catch (Exception e) {
			retornoRequisicao.setMensagem(e.getMessage());
			e.printStackTrace();
		}
		requisicao.setCharacterEncoding("utf-8");
		resposta.setCharacterEncoding("utf-8");
		resposta.setContentType("application/json");
		String retornoJson = this.gson.toJson(retornoRequisicao);
		PrintWriter pWriter = resposta.getWriter();
		pWriter.write(retornoJson);
		pWriter.flush();
	}
	public void buscarTodosProdutos(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
		RetornoRequisicao<List<ProdutoDTO>> retornoRequisicao = new RetornoRequisicao<List<ProdutoDTO>>();
		try {
			List<ProdutoDTO> produtos = this.produtoRepositorio.buscarTodos();
			if (produtos.size() == 0) {
				throw new Exception("Não existem produtos cadastrados no banco de dados!");
			}
			if (produtos.size() == 1) {
				retornoRequisicao.setMensagem("Existe 1 produto cadastrado no banco de dados!");
			} else {
				retornoRequisicao.setMensagem("Existe no total " + produtos.size() + " produtos cadastrados no banco de dados!");
			}
			retornoRequisicao.setConteudo(produtos);
		} catch (Exception e) {
			retornoRequisicao.setMensagem(e.getMessage());
			retornoRequisicao.setConteudo(new ArrayList<ProdutoDTO>());
		}
		requisicao.setCharacterEncoding("utf-8");
		resposta.setCharacterEncoding("utf-8");
		resposta.setContentType("application/json");
		String retornoJson = this.gson.toJson(retornoRequisicao);
		PrintWriter pw = resposta.getWriter();
		pw.write(retornoJson);
		pw.flush();
	}
}
