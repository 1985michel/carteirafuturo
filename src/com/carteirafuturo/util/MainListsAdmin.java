package com.carteirafuturo.util;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.HistoricoDeRentabilidade;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainListsAdmin {

	MainApp main;

	public MainListsAdmin(MainApp main) {
		this.main = main;
	}

	public TipoDeInvestimento getTipoDeInvestimentoById(String id) {

		for (TipoDeInvestimento tipo : main.aGrandeListaDeTiposDeInvestimento) {
			if (tipo.getId().equalsIgnoreCase(id))
				return tipo;
		}
		return null;
	}

	public Investidor getInvestidorById(String id) {
		for (Investidor i : main.aGrandeListaDeInvestidores) {
			if (i.getId().equalsIgnoreCase(id))
				return i;
		}
		return null;
	}

	public Corretora getCorretoraById(String id) {
		for (Corretora c : main.aGrandeListaDeCorretoras) {
			if (c.getId().equalsIgnoreCase(id))
				return c;
		}
		return null;
	}

	public HistoricoDeRentabilidade getHistoricoDeRentabilidadeByInvestimentoFXId(String id) {
		for (HistoricoDeRentabilidade h : main.aGrandeListaDeHistoricoDeRentabilidade) {
			if (h.getIdInvestimento().equalsIgnoreCase(id))
				return h;
		}
		return null;
	}

	public ObservableList<HistoricoDeRentabilidade> getTodosHistoricosDeRentabilidadePorInvestimentoFXId(String id) {

		ObservableList<HistoricoDeRentabilidade> list = FXCollections.observableArrayList();

		for (HistoricoDeRentabilidade h : main.aGrandeListaDeHistoricoDeRentabilidade) {
			if (h.getIdInvestimento().equalsIgnoreCase(id))
				list.add(h);
		}
		return list;
	}

	public double getValorInvestidoTotal() {
		double total = 0;
		for (InvestimentoFX i : this.main.aGrandeListaDeInvestimentos) {
			total += i.getAplicacaoInicial().getValorInvestido();
		}
		return total;
	}

	public double getValorAtualTotal() {
		double atual = 0;
		for (InvestimentoFX i : this.main.aGrandeListaDeInvestimentos) {
			atual += i.getValorAtual();
		}
		return atual;
	}
	
	public double getLucroTotal(){
		return getValorAtualTotal()-getValorInvestidoTotal();
	}
	
	public String getLucroPercentualString() {

		double f = getValorAtualTotal();
		double i = getValorInvestidoTotal();
		double lucro = (100 * f) / i;
		// NÃO É DINEHRIO É PORCENTAGEM
		lucro = lucro - 100;
		return String.format("%.2f", lucro);

	}
	
	public boolean temInvestimentoComAqueleTipo(TipoDeInvestimento t){
		for (InvestimentoFX i : this.main.aGrandeListaDeInvestimentos) {
			if(i.getDadosAdministrativos().getTipo().getId().equalsIgnoreCase(t.getId()))
				return true;
		}
		return false;
	}
	
	public boolean temInvestimentoComAqueleInvestidor(Investidor iv){
		for (InvestimentoFX i : this.main.aGrandeListaDeInvestimentos) {
			if(i.getDadosAdministrativos().getInvestidor().getId().equalsIgnoreCase(iv.getId()))
				return true;
		}
		return false;
	}

}
