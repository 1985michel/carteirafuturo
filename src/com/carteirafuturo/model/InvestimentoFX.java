package com.carteirafuturo.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InvestimentoFX {
	
	StringProperty id;

	// Dados Administrativos e Custos
	DadosAdministrativos dadosAdministrativos;

	// dados no momento da aplicação
	Aplicacao aplicacaoInicial;

	// dados no momento presente
	DoubleProperty rentabilidadeReal;

	// Sobre o historio de rentabilidade
	HistoricoDeRentabilidade historicoDeRentabilidade;

	// Sobre o futuro - projeções
	Projecao projecao;
	
	// Sobre Resgates
	Resgates resgates;

	// Sobre aplicações complementares
	AplicacoesAdicionais aplicacoesAdicionais;
	
	public InvestimentoFX(Aplicacao aplicacaoInicial, DadosAdministrativos dadosAdministrativos){
		this.aplicacaoInicial = aplicacaoInicial;		
		this.dadosAdministrativos = dadosAdministrativos;
		this.historicoDeRentabilidade = new HistoricoDeRentabilidade();
	}
	
	
	
	public InvestimentoFX(String id, Aplicacao aplicacaoInicial, DadosAdministrativos dadosAdministrativos){
		this(aplicacaoInicial,dadosAdministrativos);
		this.id = new SimpleStringProperty(id);
	}
	
	

	public DadosAdministrativos getDadosAdministrativos() {
		return dadosAdministrativos;
	}


	public void setDadosAdministrativos(DadosAdministrativos dadosAdministrativos) {
		this.dadosAdministrativos = dadosAdministrativos;
	}


	public Aplicacao getAplicacaoInicial() {
		return aplicacaoInicial;
	}


	public void setAplicacaoInicial(Aplicacao aplicacaoInicial) {
		this.aplicacaoInicial = aplicacaoInicial;
	}


	public HistoricoDeRentabilidade getHistoricoDeRentabilidade() {
		return historicoDeRentabilidade;
	}


	public void setHistoricoDeRentabilidade(HistoricoDeRentabilidade historicoDeRentabilidade) {
		this.historicoDeRentabilidade = historicoDeRentabilidade;
	}


	public Projecao getProjecao() {
		return projecao;
	}


	public void setProjecao(Projecao projecao) {
		this.projecao = projecao;
	}


	public Resgates getResgates() {
		return resgates;
	}


	public void setResgates(Resgates resgates) {
		this.resgates = resgates;
	}


	public AplicacoesAdicionais getAplicacoesAdicionais() {
		return aplicacoesAdicionais;
	}


	public void setAplicacoesAdicionais(AplicacoesAdicionais aplicacoesAdicionais) {
		this.aplicacoesAdicionais = aplicacoesAdicionais;
	}


	public final StringProperty idProperty() {
		return this.id;
	}
	

	public final java.lang.String getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final java.lang.String id) {
		this.id = new SimpleStringProperty(id);
	}
	

	public final DoubleProperty rentabilidadeRealProperty() {
		return this.rentabilidadeReal;
	}
	

	public final double getRentabilidadeReal() {
		return this.rentabilidadeRealProperty().get();
	}
	

	public final void setRentabilidadeReal(final double rentabilidadeReal) {
		this.rentabilidadeRealProperty().set(rentabilidadeReal);
	}
	
	
	
	
	

}
