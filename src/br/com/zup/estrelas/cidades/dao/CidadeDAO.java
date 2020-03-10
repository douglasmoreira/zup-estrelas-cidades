package br.com.zup.estrelas.cidades.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.zup.estrelas.cidades.factory.ConnectionFactory;

public class CidadeDAO {

	private static final int DATA_FUNDACAO_CIDADE = 7;
	private static final int RENDA_PER_CAPTA_CIDADE = 6;
	private static final int ESTADO_CIDADE = 5;
	private static final int CAPITAL_CIDADE = 4;
	private static final int NUMERO_HABITANTES = 3;
	private static final int NOME_CIDADE = 2;
	private static final int CEP_CIDADE = 1;
	private Connection conn;
	
	public CidadeDAO() {
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public boolean insereCidade() {
		
		String sql= "insert into cidade"
				+ "(cep,nome,nro_habitantes,capital,estado,renda_per_capita,data_fundacao)"
				+ "values (?,?,?,?,?,?,?)";
		
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(CEP_CIDADE, 1);
			stmt.setString(NOME_CIDADE, "Campinas");
			stmt.setInt(NUMERO_HABITANTES, 1200000);
			stmt.setBoolean(CAPITAL_CIDADE, false);
			stmt.setString(ESTADO_CIDADE, "sp");
			stmt.setFloat(RENDA_PER_CAPTA_CIDADE, 40000);
			stmt.setDate(DATA_FUNDACAO_CIDADE, new Date(1889, 9, 05) );
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
}
