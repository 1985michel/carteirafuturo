package com.carteirafuturo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Investidor {
	
	StringProperty id;
	StringProperty nome;

	public Investidor(String nome) {
		this.nome = new SimpleStringProperty(nome);
	}

	public Investidor(String id, String nome) {
		this(nome);
		this.id = new SimpleStringProperty(id);
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
	
	
	

}
