package com.carteirafuturo.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Meta {
	
	StringProperty id;
	StringProperty data;
	StringProperty descricao;
	DoubleProperty valorASerAtingido;
	BooleanProperty isAtingido;
	
	public Meta(String id,String data,String descricao,double valorASerAtingido,boolean isAtingido){
		this.id = new SimpleStringProperty(id);
		this.data = new SimpleStringProperty(data);
		this.descricao = new SimpleStringProperty(descricao);
		this.valorASerAtingido = new SimpleDoubleProperty(valorASerAtingido);
		this.isAtingido = new SimpleBooleanProperty(isAtingido);		
	}
	
	public Meta(String data,String descricao,double valorASerAtingido){
		this.id = new SimpleStringProperty("");
		this.data = new SimpleStringProperty(data);
		this.descricao = new SimpleStringProperty(descricao);
		this.valorASerAtingido = new SimpleDoubleProperty(valorASerAtingido);
		this.isAtingido = new SimpleBooleanProperty(false);		
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
	
	public final StringProperty descricaoProperty() {
		return this.descricao;
	}
	
	public final java.lang.String getDescricao() {
		return this.descricaoProperty().get();
	}
	
	public final void setDescricao(final java.lang.String descricao) {
		this.descricaoProperty().set(descricao);
	}
	
	public final DoubleProperty valorASerAtingidoProperty() {
		return this.valorASerAtingido;
	}
	
	public final double getValorASerAtingido() {
		return this.valorASerAtingidoProperty().get();
	}
	
	public final void setValorASerAtingido(final double valorASerAtingido) {
		this.valorASerAtingidoProperty().set(valorASerAtingido);
	}
	
	public final BooleanProperty isAtingidoProperty() {
		return this.isAtingido;
	}
	
	public final boolean isIsAtingido() {
		return this.isAtingidoProperty().get();
	}
	
	public final void setIsAtingido(final boolean isAtingido) {
		this.isAtingidoProperty().set(isAtingido);
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

	@Override
	public String toString() {
		return "Meta [id=" + id + ", data=" + data + ", descricao=" + descricao + ", valorASerAtingido="
				+ valorASerAtingido + ", isAtingido=" + isAtingido + "]";
	}
	
	
	
	
	
	
	
	

}
