package com.carteirafuturo.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe respons�vel pelo registro, gest�o e exibi��o dos dados administrativos
 * do investimento
 * 
 * A��es esperadas: - Gerenciar os registros de identifica��o e custos
 * 
 */

public class DadosAdministrativos {

	// Dados Administrativos B�sicos
	TipoDeInvestimento tipo;
	StringProperty descricao;
	DoubleProperty rentabilidadeEsperada;

	Investidor investidor;
	Corretora corretora;

	// Custos Operacionais
	CustosOperacionais custosOperacionais;

	public DadosAdministrativos(TipoDeInvestimento tipo, String descricao, double rentabilidadeEsperada, Investidor investidor,
			Corretora corretora) {
		
		this.tipo = tipo;
		this.descricao = new SimpleStringProperty(descricao);
		this.rentabilidadeEsperada = new SimpleDoubleProperty(rentabilidadeEsperada);
		this.investidor = investidor;
		this.corretora = corretora;

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
	
	
	

}
