package br.com.zup.estrelas.cidades.programaPrincipal;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.zup.estrelas.cidades.dao.CidadeDAO;
import br.com.zup.estrelas.cidades.factory.ConnectionFactory;

public class ProgramaPrincipal {

	public static void main(String[] args) throws SQLException {
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		
		cidadeDAO.insereCidade();
		System.out.println("Conectado com sucesso");
		
		
	}
}
