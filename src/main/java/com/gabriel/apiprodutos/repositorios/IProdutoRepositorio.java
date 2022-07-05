package com.gabriel.apiprodutos.repositorios;

import java.util.List;

import com.gabriel.apiprodutos.dtos.ProdutoDTO;
import com.gabriel.apiprodutos.models.Produto;

public interface IProdutoRepositorio extends IRepositorio<ProdutoDTO, Produto> {

	List<ProdutoDTO> buscarProdutoPeloNome(String nome) throws Exception;
	List<ProdutoDTO> buscarProdutosEmDestaque() throws Exception;
	List<ProdutoDTO> buscarProdutosEntrePrecosDeVenda(double precoRangeInicial, double precoRangeFinal) throws Exception;
	List<ProdutoDTO> buscarProdutosPeloIdDaCategoria(int idCategoria) throws Exception;
}
