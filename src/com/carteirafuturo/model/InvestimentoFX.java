package com.carteirafuturo.model;

import java.util.ArrayList;
import java.util.List;

import com.carteirafuturo.util.CalcularVariacao;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvestimentoFX {

	StringProperty id;

	// Dados Administrativos e Custos
	DadosAdministrativos dadosAdministrativos;

	// dados no momento da aplicação
	Aplicacao aplicacaoInicial;

	// dados no momento presente
	// Não vai para o banco
	DoubleProperty valorAtual;

	// NÃO VAI PARA O BANCO
	StringProperty lucratividade;

	// Sobre o historio de rentabilidade
	ObservableList<HistoricoDeRentabilidade> listHistoricoDeRentabilidade;

	// Sobre o futuro - projeções
	Projecao projecao;

	// Sobre Resgates
	Resgates resgates;

	// Sobre aplicações complementares
	AplicacoesAdicionais aplicacoesAdicionais;

	public InvestimentoFX(Aplicacao aplicacaoInicial, DadosAdministrativos dadosAdministrativos) {
		this.aplicacaoInicial = aplicacaoInicial;
		this.dadosAdministrativos = dadosAdministrativos;
		this.setValorAtual(this.aplicacaoInicial.getValorInvestido());
		this.listHistoricoDeRentabilidade = FXCollections.observableArrayList();

	}

	public InvestimentoFX(String id, Aplicacao aplicacaoInicial, DadosAdministrativos dadosAdministrativos) {
		this.aplicacaoInicial = aplicacaoInicial;
		this.dadosAdministrativos = dadosAdministrativos;
		this.setValorAtual(this.aplicacaoInicial.getValorInvestido());
		this.listHistoricoDeRentabilidade = FXCollections.observableArrayList();
		this.id = new SimpleStringProperty(id);
		

	}

	public InvestimentoFX(String id, Aplicacao aplicacaoInicial, DadosAdministrativos dadosAdministrativos,
			ObservableList<HistoricoDeRentabilidade> listHistoricoDeRentabilidade) {
		this.aplicacaoInicial = aplicacaoInicial;
		this.dadosAdministrativos = dadosAdministrativos;
		this.setValorAtual(this.aplicacaoInicial.getValorInvestido());
		this.id = new SimpleStringProperty(id);
		
		this.listHistoricoDeRentabilidade = listHistoricoDeRentabilidade;
		// Setando como valor atual o cultimo valor da lista ( o mais recente)
		if (!listHistoricoDeRentabilidade.isEmpty())
			this.setValorAtual(listHistoricoDeRentabilidade.get(listHistoricoDeRentabilidade.size() - 1).getValor());
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

	public ObservableList<HistoricoDeRentabilidade> getListHistoricoDeRentabilidade() {
		return this.listHistoricoDeRentabilidade;
	}

	public void setListHistoricoDeRentabilidade(ObservableList<HistoricoDeRentabilidade> listHistoricoDeRentabilidade) {
		this.listHistoricoDeRentabilidade.clear();
		this.listHistoricoDeRentabilidade.addAll(listHistoricoDeRentabilidade);

		// Setando como valor atual o cultimo valor da lista ( o mais recente)
		if (!listHistoricoDeRentabilidade.isEmpty())
			this.setValorAtual(listHistoricoDeRentabilidade.get(listHistoricoDeRentabilidade.size() - 1).getValor());
	}

	/**
	 * Ao adicionar uma nova cotação o valor atual e a lucratividade são
	 * automaticamente atualizados
	 */
	public void addListHistoricoDeRentabilidade(HistoricoDeRentabilidade hist) {
		this.listHistoricoDeRentabilidade.add(hist);
		this.setValorAtual(hist.valorProperty().get());
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

	public final DoubleProperty valorAtualProperty() {
		return this.valorAtual;
	}

	public final StringProperty valorAtualStringProperty() {
		return new SimpleStringProperty(String.valueOf(this.getValorAtual()));
	}

	public final double getValorAtual() {
		return this.valorAtualProperty().get();
	}

	public final void setValorAtual(final double valorAtual) {
		this.valorAtual = new SimpleDoubleProperty(valorAtual);
		this.lucratividade = CalcularVariacao.calcProperty(this.getAplicacaoInicial().getValorInvestido(),
				this.getValorAtual());
	}

	public final StringProperty lucratividadeProperty() {
		return this.lucratividade;
	}

	public final StringProperty lucratividadePercentualProperty() {
		return new SimpleStringProperty(this.lucratividade.get() + " %");
	}

	public final java.lang.String getLucratividade() {
		return this.lucratividadeProperty().get();
	}

	public final void setLucratividade(final java.lang.String lucratividade) {
		this.lucratividade = new SimpleStringProperty(lucratividade);
	}

}
