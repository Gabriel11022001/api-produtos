package com.gabriel.apiprodutos.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	/**
	 * Método que retorna uma instância do tipo Connection.
	 * @return Retorna um objeto do tipo Connection.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConexao() throws ClassNotFoundException, SQLException {
		String usuarioBancoDados = "root";
		String senhaUsuarioBancoDados = "";
		String nomeBancoDados = "db_api_de_produtos";
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nomeBancoDados, usuarioBancoDados, senhaUsuarioBancoDados);
	}
}
