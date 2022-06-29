package com.gabriel.apiprodutos.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gabriel.apiprodutos.dtos.CategoriaDTO;
import com.gabriel.apiprodutos.models.Categoria;
import com.gabriel.apiprodutos.repositorios.CategoriaRepositorio;
import com.gabriel.apiprodutos.utils.RetornoRequisicao;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CategoriaController {

	private Gson gson;
	private CategoriaRepositorio categoriaRepositorio;
		
	public CategoriaController() {
		this.gson = new Gson();
		this.categoriaRepositorio = new CategoriaRepositorio();
	}
	public void listarTodasAsCategorias(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
		RetornoRequisicao<List<CategoriaDTO>> retornoRequisicao = new RetornoRequisicao<List<CategoriaDTO>>();
		try {
			List<CategoriaDTO> categorias = this.categoriaRepositorio.buscarTodos();
			if (categorias.size() == 0) {
				throw new Exception("Não existem categorias cadastradas no banco de dados!");
			}
			retornoRequisicao.setConteudo(categorias);
			if (categorias.size() == 1) {
				retornoRequisicao.setMensagem("Existe 1 categoria cadastrada no banco de dados!");
			} else {
				retornoRequisicao.setMensagem("Existe um total de " + categorias.size() + " categorias cadastradas no banco de dados!");
			}
		} catch (Exception e) {
			retornoRequisicao.setMensagem(e.getMessage());
			retornoRequisicao.setConteudo(new ArrayList<CategoriaDTO>());
		}
		requisicao.setCharacterEncoding("utf-8");
		resposta.setCharacterEncoding("utf-8");
		resposta.setContentType("application/json");
		String respostaJson = this.gson.toJson(retornoRequisicao);
		PrintWriter pw = resposta.getWriter();
		pw.write(respostaJson);
		pw.flush();
	}
	public void cadastrarCategoria(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
		RetornoRequisicao<CategoriaDTO> retornoRequisicao = new RetornoRequisicao<CategoriaDTO>();
		try {
			String categoriaJson = "";
			BufferedReader bf = requisicao.getReader();
			while (bf.ready()) {
				categoriaJson += bf.readLine();
			}
			Categoria categoria = this.gson.fromJson(categoriaJson, Categoria.class);
			categoria.setDataCadastro(LocalDate.now());
			if (categoria.getDescricao().isEmpty()) {
				throw new Exception("Informe a descrição da categoria!");
			}
			CategoriaDTO categoriaDTO = this.categoriaRepositorio.salvar(categoria);
			retornoRequisicao.setMensagem("Categoria cadastrada com sucesso!");
			retornoRequisicao.setConteudo(categoriaDTO);
		} catch (Exception e) {
			retornoRequisicao.setMensagem(e.getMessage());
			retornoRequisicao.setConteudo(null);
			e.printStackTrace();
		}
		String conteudoRespostaJson = this.gson.toJson(retornoRequisicao);
		requisicao.setCharacterEncoding("utf-8");
		resposta.setCharacterEncoding("utf-8");
		resposta.setContentType("application/json");
		PrintWriter pw = resposta.getWriter();
		pw.write(conteudoRespostaJson);
		pw.flush();
	}
	public void buscarCategoriaPeloId(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
		RetornoRequisicao<CategoriaDTO> retornoRequisicao = new RetornoRequisicao<CategoriaDTO>();
		try {
			 String idCategoriaString = requisicao.getParameter("id");
			 if (idCategoriaString.isEmpty()) {
				 throw new Exception("Informe o id da categoria!");
			 }
			 int categoriaId = Integer.parseInt(idCategoriaString);
			 if (categoriaId <= 0) {
				 throw new Exception("Id inválido, o id da categoria deve ser um valor maior que 0!");
			 }
			 CategoriaDTO categoriaDTO = this.categoriaRepositorio.buscarPeloId(categoriaId);
			 if (categoriaDTO == null) {
				 throw new Exception("Não existe uma categoria cadastrada com esse id!");
			 }
			 retornoRequisicao.setMensagem("Categoria cadastrada no banco de dados!");
			 retornoRequisicao.setConteudo(categoriaDTO);
		} catch (NumberFormatException e) {
			retornoRequisicao.setMensagem("Id inválido, o id deve ser um valor numérico, inteiro e maior que 0!");
		} catch (Exception e) {
			retornoRequisicao.setMensagem(e.getMessage());
		}
		requisicao.setCharacterEncoding("utf-8");
		resposta.setCharacterEncoding("utf-8");
		resposta.setContentType("application/json");
		PrintWriter pw = resposta.getWriter();
		String retornoJson = this.gson.toJson(retornoRequisicao);
		pw.write(retornoJson);
		pw.flush();
	}
	public void removerCategoria(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
		String mensagemRetorno = "";
		try {
			String idCategoriaString = requisicao.getParameter("id");
			if (idCategoriaString.isEmpty()) {
				throw new Exception("Informe o id da categoria!");
			}
			int idCategoria = Integer.parseInt(idCategoriaString);
			if (idCategoria <= 0) {
				throw new Exception("O id da categoria deve ser maior que 0!");
			}
			
		} catch (NumberFormatException e) {
			mensagemRetorno = "Id inválido, o id da categoria deve ser um valor numérico, inteiro e maior que 0!";
		} catch (Exception e) {
			mensagemRetorno = e.getMessage();
		}
		requisicao.setCharacterEncoding("utf-8");
		resposta.setCharacterEncoding("utf-8");
		resposta.setContentType("application/json");
		String retornoJson = this.gson.toJson(mensagemRetorno);
		PrintWriter pw = resposta.getWriter();
		pw.write(retornoJson);
		pw.flush();
	}
	public void atualizarCategoria(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException {
		RetornoRequisicao<CategoriaDTO> retornoRequisicao = new RetornoRequisicao<CategoriaDTO>();
		try {
			 String categoriaAtualizarJson = "";
			 BufferedReader bf = requisicao.getReader();
			 while (bf.ready()) {
				 categoriaAtualizarJson += bf.readLine();
			 }
			 Categoria categoriaAtualizar = this.gson.fromJson(categoriaAtualizarJson, Categoria.class);
			 if (categoriaAtualizar.getId() <= 0) {
				 throw new Exception("Id inválido, o id da categoria deve ser um valor inteiro maior que 0!");
			 }
			 if (this.categoriaRepositorio.buscarPeloId(categoriaAtualizar.getId()) == null) {
				 throw new Exception("Não existe uma categoria cadastrada no banco de dados com esse id!");
			 }
			 CategoriaDTO categoriaAtualizada = this.categoriaRepositorio.atualizar(categoriaAtualizar);
			 retornoRequisicao.setMensagem("Categoria atualizada com sucesso!");
			 retornoRequisicao.setConteudo(categoriaAtualizada);
		} catch (Exception e) {
			retornoRequisicao.setMensagem(e.getMessage());
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
