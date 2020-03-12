package br.com.zup.estrelas.cidades.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.estrelas.cidades.factory.ConnectionFactory;
import br.com.zup.estrelas.cidades.pojo.Cidade;

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
	
	public boolean insereCidade(int cep,String nomeCidade, int numeroHabitantes, boolean capitalCidade,
			String estadoCidade, float rendaPerCapita, String dataFundacao ) {
		
		String sql= "insert into cidade"
				+ "(cep,nome,nro_habitantes,capital,estado,renda_per_capita,data_fundacao)"
				+ "values (?,?,?,?,?,?,?)";
		
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(CEP_CIDADE, cep);
			stmt.setString(NOME_CIDADE, nomeCidade);
			stmt.setInt(NUMERO_HABITANTES, numeroHabitantes);
			stmt.setBoolean(CAPITAL_CIDADE, capitalCidade);
			stmt.setString(ESTADO_CIDADE, estadoCidade);
			stmt.setFloat(RENDA_PER_CAPTA_CIDADE, rendaPerCapita);
			stmt.setString(DATA_FUNDACAO_CIDADE, dataFundacao);
			
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public List<Cidade> listaCidades() {
		
		List<Cidade> cidades = new ArrayList<>();
		
		String sql = "select * from cidade";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Cidade cidade = new Cidade();
				criaObjetoCidade(rs);
				cidades.add(cidade);
			}
			stmt.close();
			
		} catch (SQLException e) {
			System.err.printf(e.getMessage());
		}
		
		return cidades;
	}
	
	public Cidade buscaCidadePeloCep(int cep) {
		
		Cidade cidade = new Cidade();
		String sql = "select * from cidade where cep = ?";
			
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cep);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			cidade = criaObjetoCidade(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cidade;
	}
	
	public void removeCidade(int cep) {
		
		String sql = "delete from cidade "
				+ "where cep = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, cep);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Cidade buscaCidadePeloNome(String nome) {
		
		Cidade cidade = new Cidade();
		String sql = "select * from cidade c where c.nome like ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,"%"+ nome + "%");
			ResultSet rs = stmt.executeQuery();
			rs.next();
			cidade = criaObjetoCidade(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cidade;
	}
	
	public List<Cidade> buscaCidadesPeloEstado(String estado) {
		
		Cidade cidade = new Cidade();
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		String sql = "select * from cidade "
				+ "where estado = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, estado);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cidade = criaObjetoCidade(rs);
				cidades.add(cidade);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cidades;
	}

	public Cidade criaObjetoCidade(ResultSet rs ) throws SQLException {
		
		Cidade cidade = new Cidade();
		cidade.setCep(rs.getInt("cep"));
		cidade.setNome(rs.getString("nome"));
		cidade.setEstado(rs.getString("estado"));
		cidade.setCapital(rs.getBoolean("capital"));
		cidade.setRendaPerCapita(rs.getFloat("renda_per_capita"));
		cidade.setNumeroDeHabitantes(rs.getInt("nro_habitantes"));
		cidade.setDataDeFuncacao(rs.getString("data_fundacao"));
		return cidade;
	}
	
	public int qualQuantidadeCidadesPorEstados(String estado) {
		
		String sql = "select count(*) " //"select count(*) as qnt_cidade_estado
				+ "from cidade c "
				+ "where c.estado = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, estado);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<Cidade> buscaCapitais(boolean capital) {
		
		Cidade cidade = new Cidade();
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		String sql = "select * from cidade "
				+ "where capital = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, capital);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				cidade = criaObjetoCidade(rs);
				cidades.add(cidade);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cidades;
	}
}
