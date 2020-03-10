package br.com.zup.estrelas.cidades.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/estrelas?user=root&password=Bubu");
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Falha na conexão");
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	

}
