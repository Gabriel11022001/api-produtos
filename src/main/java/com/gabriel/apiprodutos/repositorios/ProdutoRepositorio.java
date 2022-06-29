package com.gabriel.apiprodutos.repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gabriel.apiprodutos.dtos.CategoriaDTO;
import com.gabriel.apiprodutos.dtos.ProdutoDTO;
import com.gabriel.apiprodutos.models.Produto;
import com.gabriel.apiprodutos.utils.Conexao;

public class ProdutoRepositorio implements IProdutoRepositorio {

	@Override
	public ProdutoDTO salvar(Produto obj) throws Exception {
		ProdutoDTO produtoDTO = null;
		String query = "INSERT INTO tbl_produtos(produto_nome, produto_descricao_resumida, produto_descricao_completa, produto_preco_venda, produto_em_destaque, produto_data_cadastro, produto_quantidade_unidades_em_estoque, categoria_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		Connection conexao = Conexao.getConexao();
		PreparedStatement stmt = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, obj.getNome());
		stmt.setString(2, obj.getDescricaoResumida());
		stmt.setString(3, obj.getDescricaoCompleta());
		stmt.setDouble(4, obj.getPrecoVenda());
		stmt.setBoolean(5, obj.isEmDestaque());
		Date dataCadastro = Date.valueOf(obj.getDataCadastro());
		stmt.setDate(6, dataCadastro);
		stmt.setInt(7, obj.getQuantidadeUnidadesEmEstoque());
		stmt.setInt(8, obj.getCategoriaId());
		int quantidadeLinhasAfetadas = stmt.executeUpdate();
		if (quantidadeLinhasAfetadas > 0) {
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				produtoDTO = this.buscarPeloId(rs.getInt(1));
			}
		}
		conexao.close();
		return produtoDTO;
	}
	@Override
	public ProdutoDTO atualizar(Produto obj) throws Exception {
		
		return null;
	}
	@Override
	public List<ProdutoDTO> buscarTodos() throws Exception {
		List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
		String query = "SELECT * FROM tbl_produtos;";
		Connection conexao = Conexao.getConexao();
		PreparedStatement stmt = conexao.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.setId(rs.getInt("produto_id"));
			produtoDTO.setNome(rs.getString("produto_nome"));
			produtoDTO.setDescricaoResumida(rs.getString("produto_descricao_resumida"));
			produtoDTO.setDescricaoCompleta(rs.getString("produto_descricao_completa"));
			produtoDTO.setEmDestaque(rs.getBoolean("produto_em_destaque"));
			produtoDTO.setPrecoVenda(rs.getDouble("produto_preco_venda"));
			produtoDTO.setQuantidadeUnidadesEmEstoque(rs.getInt("produto_quantidade_unidades_em_estoque"));
			produtoDTO.setDataCadastro(rs.getDate("produto_data_cadastro").toLocalDate());
			int idCategoria = rs.getInt("categoria_id");
			CategoriaDTO categoriaDTO = new CategoriaRepositorio().buscarPeloId(idCategoria);
			produtoDTO.setCategoria(categoriaDTO);
			produtos.add(produtoDTO);
		}
		conexao.close();
		return produtos;
	}
	@Override
	public ProdutoDTO buscarPeloId(int id) throws Exception {
		ProdutoDTO produtoDTO = null;
		String query = "SELECT * FROM tbl_produtos WHERE produto_id = ?;";
		Connection conexao = Conexao.getConexao();
		PreparedStatement stmt = conexao.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			produtoDTO = new ProdutoDTO();
			produtoDTO.setId(rs.getInt("produto_id"));
			produtoDTO.setNome(rs.getString("produto_nome"));
			produtoDTO.setDescricaoResumida(rs.getString("produto_descricao_resumida"));
			produtoDTO.setDescricaoCompleta(rs.getString("produto_descricao_completa"));
			produtoDTO.setEmDestaque(rs.getBoolean("produto_em_destaque"));
			produtoDTO.setPrecoVenda(rs.getDouble("produto_preco_venda"));
			LocalDate dataCadastro = rs.getDate("produto_data_cadastro").toLocalDate();
			produtoDTO.setDataCadastro(dataCadastro);
			produtoDTO.setQuantidadeUnidadesEmEstoque(rs.getInt("produto_quantidade_unidades_em_estoque"));
			CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();
			CategoriaDTO categoriaDTO = categoriaRepositorio.buscarPeloId(rs.getInt("categoria_id"));
			produtoDTO.setCategoria(categoriaDTO);
		}
		conexao.close();
		return produtoDTO;
	}
	@Override
	public void remover(int id) throws Exception {
		
	}
	@Override
	public ProdutoDTO buscarProdutoPeloNome(String nome) throws Exception {
		
		return null;
	}
	@Override
	public ProdutoDTO buscarProdutoPelaDescricaoResumida(String descricaoResumida) throws Exception {
		
		return null;
	}
	@Override
	public List<ProdutoDTO> buscarProdutosEmDestaque() throws Exception {
		
		return null;
	}
	@Override
	public List<ProdutoDTO> buscarProdutosEntrePrecosDeVenda(double precoRangeInicial, double precoRangeFinal) throws Exception {
		
		return null;
	}
	@Override
	public List<ProdutoDTO> buscarProdutosPeloIdDaCategoria(int idCategoria) throws Exception {
		
		return null;
	}
}
