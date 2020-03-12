package br.com.zup.estrelas.cidades.pojo;

import java.sql.Date;

public class Cidade {
	

	private int cep;
	private String nome;
	private int numeroDeHabitantes;
	private boolean capital;
	private String estado;
	private float rendaPerCapita;
	private String dataDeFuncacao;
	
	public Cidade() {
	}

	public int getCep() {
		return cep;
	}
	
	public void setCep(int cep) {
		this.cep = cep;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getNumeroDeHabitantes() {
		return numeroDeHabitantes;
	}
	
	public void setNumeroDeHabitantes(int numeroDeHabitantes) {
		this.numeroDeHabitantes = numeroDeHabitantes;
	}
	
	public boolean getCapital() {
		return capital;
	}
	
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public float getRendaPerCapita() {
		return rendaPerCapita;
	}
	
	public void setRendaPerCapita(float rendaPerCapita) {
		this.rendaPerCapita = rendaPerCapita;
	}
	
	public String getDataDeFuncacao() {
		return dataDeFuncacao;
	}
	
	public void setDataDeFuncacao(String dataDeFuncacao) {
		this.dataDeFuncacao = dataDeFuncacao;
	}
	
}
