package com.carteirafuturo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TipoDeInvestimento {
	
	public static ObservableList<String> prazosObservable = FXCollections.observableArrayList();
	
	StringProperty id;
	StringProperty nome;
	StringProperty prazo;
	
	static String[] prazos = {"RESERVA DE EMERGÊNCIA","CURTO","MEDIO","LONGO"};
	
	
	static{
		prazosObservable.addAll(prazos);		
	}
	
	public TipoDeInvestimento(String nome){
		this.nome = new SimpleStringProperty(nome);
		this.id = new SimpleStringProperty("");		
		this.prazo = new SimpleStringProperty("");
	}
	
	public TipoDeInvestimento(String nome, int prazo){
		this.nome = new SimpleStringProperty(nome);
		this.id = new SimpleStringProperty("");
		this.prazo = new SimpleStringProperty(prazos[prazo]);
	}
	
	public TipoDeInvestimento(String id, String nome){
		this.nome = new SimpleStringProperty(nome);
		this.id = new SimpleStringProperty(id);
	}
	
	public TipoDeInvestimento(String id, String nome, int prazo){
		this.nome = new SimpleStringProperty(nome);
		this.id = new SimpleStringProperty(id);
		this.prazo = new SimpleStringProperty(prazos[prazo]);
	}
	
	public TipoDeInvestimento(String id, String nome, String prazo){
		this.nome = new SimpleStringProperty(nome);
		this.id = new SimpleStringProperty(id);
		this.prazo = new SimpleStringProperty(prazo);
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
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final java.lang.String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final java.lang.String nome) {
		this.nomeProperty().set(nome);
	}
	
	@Override
	public String toString() {
		// return "TipoDeInvestimento [id=" + id + ", nome=" + nome + ",
		// calculadoraDeCustos=" + calculadoraDeCustos.getId() + "]";
		return this.getNome();
	}

	public final StringProperty prazoProperty() {
		return this.prazo;
	}
	

	public final java.lang.String getPrazo() {
		return this.prazoProperty().get();
	}
	

	public final void setPrazo(final int prazo) {
		this.prazoProperty().set(prazos[prazo]);
	}
	
	public final void setPrazo(final java.lang.String prazo) {
		this.prazoProperty().set(prazo);
	}
	
	
	
	

}
