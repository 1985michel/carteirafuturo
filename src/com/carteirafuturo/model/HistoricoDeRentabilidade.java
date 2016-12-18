package com.carteirafuturo.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HistoricoDeRentabilidade {

	StringProperty id;
	StringProperty idInvestimento;
	StringProperty data;
	DoubleProperty valor;

	public HistoricoDeRentabilidade(String id, String idInvestimento, String data, double valor) {

		this.id = new SimpleStringProperty(id);
		this.idInvestimento = new SimpleStringProperty(idInvestimento);
		this.data = new SimpleStringProperty(data);
		this.valor = new SimpleDoubleProperty(valor);
	}

	public HistoricoDeRentabilidade(String idInvestimento, String data, double valor) {

		this.idInvestimento = new SimpleStringProperty(idInvestimento);
		this.data = new SimpleStringProperty(data);
		this.valor = new SimpleDoubleProperty(valor);
	}

	public final StringProperty idProperty() {
		return this.id;
	}

	public final java.lang.String getId() {
		return this.idProperty().get();
	}

	public final void setId(final java.lang.String id) {
		this.idProperty().set(id);
	}

	public final StringProperty idInvestimentoProperty() {
		return this.idInvestimento;
	}

	public final java.lang.String getIdInvestimento() {
		return this.idInvestimentoProperty().get();
	}

	public final void setIdInvestimento(final java.lang.String idInvestimento) {
		this.idInvestimentoProperty().set(idInvestimento);
	}

	public final StringProperty dataProperty() {
		return this.data;
	}

	public final java.lang.String getData() {
		return this.dataProperty().get();
	}

	public final void setData(final java.lang.String data) {
		this.dataProperty().set(data);
	}

	public final DoubleProperty valorProperty() {
		return this.valor;
	}

	public final double getValor() {
		return this.valorProperty().get();
	}

	public final void setValor(final double valor) {
		this.valorProperty().set(valor);
	}

}
