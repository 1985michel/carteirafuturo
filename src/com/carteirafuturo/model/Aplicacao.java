package com.carteirafuturo.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Aplicacao {
	
	StringProperty dataInvestimento;
	DoubleProperty valorInvestido;
	
	
	
	public Aplicacao(String dataInvestimento, double valorInvestido){
		this.dataInvestimento = new SimpleStringProperty(dataInvestimento);
		this.valorInvestido = new SimpleDoubleProperty(valorInvestido);		
	}
	
	
	public final StringProperty dataInvestimentoProperty() {
		return this.dataInvestimento;
	}
	
	public final java.lang.String getDataInvestimento() {
		return this.dataInvestimentoProperty().get();
	}
	
	public final void setDataInvestimento(final java.lang.String dataInvestimento) {
		this.dataInvestimentoProperty().set(dataInvestimento);
	}
	
	public final DoubleProperty valorInvestidoProperty() {
		return this.valorInvestido;
	}
	
	public final StringProperty valorInvestidoStringProperty(){
		String valor = String.valueOf(this.valorInvestidoProperty());
		return new SimpleStringProperty(valor);
	}
	
	public final double getValorInvestido() {
		return this.valorInvestidoProperty().get();
	}
	
	public final void setValorInvestido(final double valorInvestido) {
		this.valorInvestidoProperty().set(valorInvestido);
	}

}
