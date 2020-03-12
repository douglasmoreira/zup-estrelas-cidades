package br.com.zup.estrelas.cidades.programaPrincipal;

import java.sql.SQLException;
import java.util.Scanner;
import br.com.zup.estrelas.cidades.dao.CidadeDAO;
import br.com.zup.estrelas.cidades.pojo.Cidade;

public class ProgramaPrincipal {

	public static void main(String[] args) throws SQLException{
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Scanner input = new Scanner(System.in);
		
		int opcao = 0;
		do {
			System.out.printf("%-45s %s\n","função"," codigo");
			System.out.printf("%-45s %s\n","Para inserir uma cidade."," digite  1:");
			System.out.printf("%-45s %s\n","Para ver todos os conteúdos da tabela."," digite  2:");
			System.out.printf("%-45s %s\n","Para remover uma cidade da tabela."," digite  3:");
			System.out.printf("%-45s %s\n","Para buscar uma cidade pelo cep."," digite  4:");
			System.out.printf("%-45s %s\n","Para buscar uma cidade pelo nome."," digite  5:");
			System.out.printf("%-45s %s\n","Para buscar cidade pelo estado."," digite  6:");
			System.out.printf("%-45s %s\n","Para saber a quantidade de cidade por estado."," digite  7:");
			System.out.printf("%-45s %s\n","Para buscar capitais ou não."," digite  8:");
			System.out.printf("%-45s %s\n","Para terminar o Programa."," digite -1:");
			opcao = input.nextInt();
			
			if (opcao == -1) {
				break;
			}
			
			switch (opcao) {
			case 1:
				adicionaCidade(cidadeDAO, input);				
				break;
			case 2:
				listaCidades(cidadeDAO);
				break;
			case 3:
				removeCidade(cidadeDAO, input);
				break;
			case 4:
				buscaUmaCidadePeloCep(cidadeDAO, input);
				break;
			case 5:
				buscaUmaCidadePeloNome(cidadeDAO, input);
				break;
			case 6:
				buscaCidadePeloEstado(cidadeDAO, input);
				break;
			case 7:
				quantidadeCidadePorEstado(cidadeDAO, input);
				break;
			case 8:
				descobrirCapital(cidadeDAO, input);
				break;
			}
			System.out.println();
		} while(opcao != -1);
		
		
		
		System.out.println("Conectado com sucesso");
		
		input.close();
	}

	public static void adicionaCidade(CidadeDAO cidadeDAO, Scanner input) {
		System.out.println("Digite o nome da cidade:");
		String nomeCidade = input.next();
		System.out.println("Digite o cep da cidade:");
		int cep = input.nextInt();
		System.out.println("Digite o nome do estado:");
		String estadoCidade = input.next();
		System.out.println("Digite se é uma capital ou não");
		boolean capital = input.nextBoolean();
		System.out.println("Digite número de habitantes:");
		int numeroHabitantes = input.nextInt();
		System.out.println("Digite a data de fundação");
		String dataFundacao = input.next();
		System.out.println("Digite a renda percapta:");
		float rendaPerCapita = input.nextFloat();
		
		cidadeDAO.insereCidade(cep,nomeCidade,numeroHabitantes,capital, estadoCidade,rendaPerCapita,dataFundacao);
	}
	
	public static void listaCidades(CidadeDAO cidadeDAO) {
		
		System.out.printf("%-11s %-24s %-7s %-7s %-8s %-8s %11s \n\n","cep","nome","Estado", "capital", 
				"Renda percapta","Número de habitantes", "Data de Fundação");
		
		for(Cidade cidade : cidadeDAO.listaCidades()) {
			geraImpressaoDaTabela(cidade);
		}
	}
	
	public static void removeCidade(CidadeDAO cidadeDAO, Scanner input) {
		System.out.println("Digite o cep da cidade a remover");
		int cep = input.nextInt();
		cidadeDAO.removeCidade(cep);
	}
	
	public static void buscaUmaCidadePeloCep(CidadeDAO cidadeDAO, Scanner input) {
		System.out.println("Digite o cep da cidade a ser encontrada");
		int cep = input.nextInt();
		Cidade cidade = cidadeDAO.buscaCidadePeloCep(cep);
		
		System.out.printf("%-11s %-24s %-7s %-7s %-8s %-8s %11s \n","cep","nome","Estado", "capital", 
				"Renda percapta","Número de habitantes", "Data de Fundação");
		geraImpressaoDaTabela(cidade);
	}
	
	public static void buscaUmaCidadePeloNome(CidadeDAO cidadeDAO, Scanner input) {

		System.out.println("Digite o nome da cidade:");
		String nome = input.next();
		Cidade cidade = cidadeDAO.buscaCidadePeloNome(nome);
		
		System.out.printf("%-11s %-24s %-7s %-7s %-8s %-8s %11s \n","cep","nome","Estado", "capital", 
				"Renda percapta","Número de habitantes", "Data de Fundação");
		geraImpressaoDaTabela(cidade);
	}
	
	public static void buscaCidadePeloEstado(CidadeDAO cidadeDAO, Scanner input) {
		
		System.out.println("Digite a sigla do estado:");
		String estado = input.next();
		
		System.out.printf("%-11s %-24s %-7s %-7s %-8s %-8s %11s \n","cep","nome","Estado", "capital", 
				"Renda percapta","Número de habitantes", "Data de Fundação");
		
		for (Cidade cidade : cidadeDAO.buscaCidadesPeloEstado(estado)) {
			geraImpressaoDaTabela(cidade);
		}
	}

	public static void geraImpressaoDaTabela(Cidade cidade) {
		System.out.printf("%-11d ",cidade.getCep());
		System.out.printf("%-25s",cidade.getNome());
		System.out.printf("%-8s",cidade.getEstado());
		System.out.printf("%-8b",cidade.getCapital());
		System.out.printf("%-15f",cidade.getRendaPerCapita());
		System.out.printf("%-21d",cidade.getNumeroDeHabitantes());
		System.out.printf("%-11s \n",cidade.getDataDeFuncacao());
	}
	
	public static void quantidadeCidadePorEstado(CidadeDAO cidadeDAO, Scanner input) {

		System.out.println("Digite a sigla do estado:");
		String estado = input.next();
		System.out.printf("\nA quantidade de cidade do estado %s é de %d\n",estado, cidadeDAO.qualQuantidadeCidadesPorEstados(estado));
	}

	public static void descobrirCapital(CidadeDAO cidadeDAO, Scanner input) {
		
		System.out.println("Digite true para obter as captais e false para obter cidades que não sejam capitais.");
		boolean capital = input.nextBoolean();
		for (Cidade cidade : cidadeDAO.buscaCapitais(capital)) {
			geraImpressaoDaTabela(cidade);
		}
	}
}

	
