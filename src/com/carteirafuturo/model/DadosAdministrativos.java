package com.carteirafuturo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe responsável pelo registro, gestão e exibição dos dados administrativos
 * do investimento
 * 
 * Ações esperadas: - Gerenciar os registros de identificação e custos
 * 
 */

public class DadosAdministrativos {

	// Dados Administrativos Básicos
	TipoDeInvestimento tipo;
	StringProperty descricao;
	DoubleProperty rentabilidadeEsperada;
	StringProperty plano;
	BooleanProperty isResgatado;

	Investidor investidor;
	Corretora corretora;

	// Custos Operacionais
	CustosOperacionais custosOperacionais;

	public DadosAdministrativos(TipoDeInvestimento tipo, String descricao, double rentabilidadeEsperada, String plano, Investidor investidor,
			Corretora corretora) {
		
		this.tipo = tipo;
		this.descricao = new SimpleStringProperty(descricao);
		this.rentabilidadeEsperada = new SimpleDoubleProperty(rentabilidadeEsperada);
		this.plano = new SimpleStringProperty(plano);
		this.investidor = investidor;
		this.corretora = corretora;
		this.isResgatado = new SimpleBooleanProperty(false);

	}
	
	public DadosAdministrativos(TipoDeInvestimento tipo, String descricao, double rentabilidadeEsperada, String plano, Investidor investidor,
			Corretora corretora, boolean isResgatado) {
		
		this.tipo = tipo;
		this.descricao = new SimpleStringProperty(descricao);
		this.rentabilidadeEsperada = new SimpleDoubleProperty(rentabilidadeEsperada);
		this.plano = new SimpleStringProperty(plano);
		this.investidor = investidor;
		this.corretora = corretora;
		this.isResgatado = new SimpleBooleanProperty(isResgatado);

	}

	public TipoDeInvestimento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeInvestimento tipo) {
		this.tipo = tipo;
	}

	public Investidor getInvestidor() {
		return investidor;
	}

	public void setInvestidor(Investidor investidor) {
		this.investidor = investidor;
	}

	public Corretora getCorretora() {
		return corretora;
	}

	public void setCorretora(Corretora corretora) {
		this.corretora = corretora;
	}

	public CustosOperacionais getCustosOperacionais() {
		return custosOperacionais;
	}

	public void setCustosOperacionais(CustosOperacionais custosOperacionais) {
		this.custosOperacionais = custosOperacionais;
	}

	public final StringProperty descricaoProperty() {
		return this.descricao;
	}

	public final java.lang.String getDescricao() {
		return this.descricaoProperty().get();
	}

	public final void setDescricao(final java.lang.String descricao) {
		this.descricaoProperty().set(descricao);
	}

	public final DoubleProperty rentabilidadeEsperadaProperty() {
		return this.rentabilidadeEsperada;
	}
	

	public final double getRentabilidadeEsperada() {
		return this.rentabilidadeEsperadaProperty().get();
	}
	

	public final void setRentabilidadeEsperada(final double rentabilidadeEsperada) {
		this.rentabilidadeEsperadaProperty().set(rentabilidadeEsperada);
	}

	public final StringProperty planoProperty() {
		return this.plano;
	}
	

	public final java.lang.String getPlano() {
		return this.planoProperty().get();
	}
	

	public final void setPlano(final java.lang.String plano) {
		this.planoProperty().set(plano);
	}

	public final BooleanProperty isResgatadoProperty() {
		return this.isResgatado;
	}
	

	public final boolean isResgatado() {
		return this.isResgatadoProperty().get();
	}
	

	public final void setIsResgatado(final boolean isResgatado) {
		this.isResgatadoProperty().set(isResgatado);
	}
	
	
	
	
	

}
