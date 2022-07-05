package com.gabriel.apiprodutos.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
		"/categorias",
		"/categorias/cadastrar",
		"/categorias/consultar",
		"/categorias/remover",
		"/categorias/atualizar",
		"/produtos/cadastrar",
		"/produtos",
		"/produtos/consultar",
		"/produtos/remover",
		"/produtos/listar-produtos-em-destaque",
		"/produtos/buscar-pelo-nome",
		"/produtos/buscar-entre-precos",
		"/produtos/buscar-pela-categoria"
})
public class Controller extends HttpServlet {

	public Controller() {
		super();
	}
	@Override
	protected void service(HttpServletRequest requisicao, HttpServletResponse resposta) throws ServletException, IOException {
		// /api-produtos/
		String endpoint = requisicao.getRequestURI().substring(13);
		if (endpoint.equals("/categorias")) {
			CategoriaController categoriaController = new CategoriaController();
			categoriaController.listarTodasAsCategorias(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/categorias/cadastrar")) {
			CategoriaController categoriaController = new CategoriaController();
			categoriaController.cadastrarCategoria(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/categorias/consultar")) {
			CategoriaController categoriaController = new CategoriaController();
			categoriaController.buscarCategoriaPeloId(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/categorias/remover")) {
			CategoriaController categoriaController = new CategoriaController();
			categoriaController.removerCategoria(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/categorias/atualizar")) {
			CategoriaController categoriaController = new CategoriaController();
			categoriaController.atualizarCategoria(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/produtos/cadastrar")) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.cadastrarProduto(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/produtos")) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.buscarTodosProdutos(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/produtos/consultar")) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.buscarProdutoPeloId(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/produtos/remover")) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.removerProduto(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/produtos/listar-produtos-em-destaque")) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.buscarProdutosEmDestaque(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/produtos/buscar-pelo-nome")) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.buscarProdutoPeloNome(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/produtos/buscar-entre-precos")) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.buscarProdutosEntrePrecos(requisicao, resposta);
			return;
		}
		if (endpoint.equals("/produtos/buscar-pela-categoria")) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.buscarProdutoPelaCategoria(requisicao, resposta);
			return;
		}
	}
}
