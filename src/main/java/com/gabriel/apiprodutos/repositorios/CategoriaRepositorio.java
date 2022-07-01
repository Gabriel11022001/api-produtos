package com.gabriel.apiprodutos.repositorios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gabriel.apiprodutos.dtos.CategoriaDTO;
import com.gabriel.apiprodutos.models.Categoria;
import com.gabriel.apiprodutos.utils.Conexao;

public class CategoriaRepositorio implements ICategoriaRepositorio {

	@Override
	public CategoriaDTO salvar(Categoria obj) throws Exception {
		CategoriaDTO categoriaDTO = null;
		String query = "INSERT INTO tbl_categorias(categoria_descricao, categoria_data_cadastro) VALUES(?, ?);";
		Connection conexao = Conexao.getConexao();
		PreparedStatement stmt = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, obj.getDescricao());
		Date dataCadastro = Date.valueOf(obj.getDataCadastro());
		stmt.setDate(2, dataCadastro);
		int quantidadeLinhasAfetadas = stmt.executeUpdate();
		if (quantidadeLinhasAfetadas > 0) {
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				categoriaDTO = this.buscarPeloId(rs.getInt(1));
			}
		}
		conexao.close();
		return categoriaDTO;
	}
	@Override
	public CategoriaDTO atualizar(Categoria obj) throws Exception {
		String query = "UPDATE tbl_categorias SET categoria_descricao = ? WHERE categoria_id = ?;";
		Connection conexao = Conexao.getConexao();
		PreparedStatement stmt = conexao.prepareStatement(query);
		stmt.setString(1, obj.getDescricao());
		stmt.setInt(2, obj.getId());
		stmt.execute();
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setId(obj.getId());
		categoriaDTO.setDescricao(obj.getDescricao());
		categoriaDTO.setDataCadastro(obj.getDataCadastro());
		conexao.close();
		return categoriaDTO;
	}
	@Override
	public List<CategoriaDTO> buscarTodos() throws Exception {
		List<CategoriaDTO> categorias = new ArrayList<CategoriaDTO>();
		String query = "SELECT * FROM tbl_categorias;";
		Connection conexao = Conexao.getConexao();
		PreparedStatement stmt = conexao.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			CategoriaDTO categoriaDTO = new CategoriaDTO();
			categoriaDTO.setDataCadastro(rs.getDate("categoria_data_cadastro").toLocalDate());
			categoriaDTO.setDescricao(rs.getString("categoria_descricao"));
			categoriaDTO.setId(rs.getInt("categoria_id"));
			categorias.add(categoriaDTO);
		}
		conexao.close();
		return categorias;
	}
	@Override
	public CategoriaDTO buscarPeloId(int id) throws Exception {
		String query = "SELECT * FROM tbl_categorias WHERE categoria_id = ?;";
		Connection conexao = Conexao.getConexao();
		PreparedStatement stmt = conexao.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			CategoriaDTO categoriaDTO = new CategoriaDTO();
			categoriaDTO.setId(rs.getInt("categoria_id"));
			categoriaDTO.setDescricao(rs.getString("categoria_descricao"));
			categoriaDTO.setDataCadastro(rs.getDate("categoria_data_cadastro").toLocalDate());
			conexao.close();
			return categoriaDTO;
		}
		conexao.close();
		return null;
	}
	@Override
	public void remover(int id) throws Exception {
		String query = "DELETE FROM tbl_categorias WHERE categoria_id = ?;";
		Connection conexao = Conexao.getConexao();
		PreparedStatement stmt = conexao.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.execute();
		conexao.close();
	}
}
