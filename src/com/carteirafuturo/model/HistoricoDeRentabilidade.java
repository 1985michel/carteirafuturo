package com.carteirafuturo.model;

import java.util.HashMap;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HistoricoDeRentabilidade {

	StringProperty id;
	StringProperty idInvestimento;
	//Par data valor
	HashMap<StringProperty, DoubleProperty> mapHistoricoDeRentabilidade;
	
	public HistoricoDeRentabilidade(){
		this.mapHistoricoDeRentabilidade = new HashMap<>();
	}
	
	public HistoricoDeRentabilidade(String idInvestimento,String data, double valor){
		this();
		this.idInvestimento = new SimpleStringProperty(idInvestimento);
		this.mapHistoricoDeRentabilidade.put(new SimpleStringProperty(data), new SimpleDoubleProperty(valor));
	}
	
	public HistoricoDeRentabilidade(String id, String idInvestimento,String data, double valor){
		this(idInvestimento,data,valor);
		this.id = new SimpleStringProperty(id);
	}
	
		
	public HashMap<StringProperty, DoubleProperty> getMapHistoricoDeRentabilidade() {
		return mapHistoricoDeRentabilidade;
	}

	public void setMapHistoricoDeRentabilidade(HashMap<StringProperty, DoubleProperty> mapHistoricoDeRentabilidade) {
		this.mapHistoricoDeRentabilidade = mapHistoricoDeRentabilidade;
	}
	
	public void addMapHistoricoDeRentabilidade(String data, double valor){
		this.mapHistoricoDeRentabilidade.put(new SimpleStringProperty(data), new SimpleDoubleProperty(valor));
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
	
	
	
	
	
}
