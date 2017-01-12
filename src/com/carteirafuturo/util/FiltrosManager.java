package com.carteirafuturo.util;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FiltrosManager {

	public ObservableList<InvestimentoFX> listaFiltrada = FXCollections.observableArrayList();
	MainApp mainApp;

	public FiltrosManager(MainApp mainApp) {
		this.mainApp = mainApp;
		povoarListaComTodosInvestimentos();
	}

	private void povoarListaComTodosInvestimentos() {
		this.listaFiltrada = this.mainApp.aGrandeListaDeInvestimentos;
	}
	
	private void povoarListaComTodosInvestimentosAtivos() {
		this.listaFiltrada = this.mainApp.aGrandeListaDeInvestimentosAtivos;
	}
	
	private void povoarListaComTodosInvestimentosResgatados() {
		this.listaFiltrada = this.mainApp.aGrandeListaDeInvestimentosResgatados;
	}

	public ObservableList<InvestimentoFX> filtrar(String status,ObservableList<TipoDeInvestimento> tipos,
			ObservableList<String> prazos, ObservableList<Corretora> corretoras,
			ObservableList<Investidor> investidores) {

		if(status.equalsIgnoreCase(DadosAdministrativos.statusDeInvestimentos.get(0)))
			povoarListaComTodosInvestimentos();
		else if(status.equalsIgnoreCase(DadosAdministrativos.statusDeInvestimentos.get(1)))
			povoarListaComTodosInvestimentosAtivos();
		else if(status.equalsIgnoreCase(DadosAdministrativos.statusDeInvestimentos.get(2)))
			povoarListaComTodosInvestimentosResgatados();
		
		this.listaFiltrada = filtrarPorTipoDeInvestimento(tipos);
		this.listaFiltrada = filtrarPorInvestidor(investidores);
		this.listaFiltrada = filtrarPorCorretora(corretoras);
		this.listaFiltrada = filtrarPorPrazo(prazos);
		return listaFiltrada;
	}

	private ObservableList<InvestimentoFX> filtrarPorTipoDeInvestimento(ObservableList<TipoDeInvestimento> tipos) {

		// se for todos, devolva a propría lista
		// if (tipos.size() == 1)
		for (TipoDeInvestimento t : tipos) {
			if (t.getId().equalsIgnoreCase("-1"))
				return listaFiltrada;
		}

		// se não for todos
		ObservableList<InvestimentoFX> listaInterna = FXCollections.observableArrayList();

		for (InvestimentoFX i : listaFiltrada) {
			for (TipoDeInvestimento tipo : tipos) {
				if (i.getDadosAdministrativos().getTipo().getId().equalsIgnoreCase(tipo.getId()))
					if (!listaInterna.contains(i))
						listaInterna.add(i);
			}
		}
		return listaInterna;
	}

	private ObservableList<InvestimentoFX> filtrarPorPrazo(ObservableList<String> prazos) {

		// se for todos, devolva a propría lista
		for (String p : prazos) {
			if (p.equalsIgnoreCase("Todos os Prazos"))
				return listaFiltrada;
		}

		// se não for todos
		ObservableList<InvestimentoFX> listaInterna = FXCollections.observableArrayList();

		for (InvestimentoFX i : listaFiltrada) {
			for (String p : prazos) {
				if (i.getDadosAdministrativos().getTipo().getPrazo().equalsIgnoreCase(p))
					if (!listaInterna.contains(i))
						listaInterna.add(i);
			}
		}
		return listaInterna;
	}

	private ObservableList<InvestimentoFX> filtrarPorInvestidor(ObservableList<Investidor> investidores) {

		// se for todos, devolva a propría lista
		// if (tipos.size() == 1)
		for (Investidor iv : investidores) {
			if (iv.getId().equalsIgnoreCase("-1"))
				return listaFiltrada;
		}

		// se não for todos
		ObservableList<InvestimentoFX> listaInterna = FXCollections.observableArrayList();

		for (InvestimentoFX i : listaFiltrada) {
			for (Investidor iv : investidores) {
				if (i.getDadosAdministrativos().getInvestidor().getId().equalsIgnoreCase(iv.getId()))
					if (!listaInterna.contains(i))
						listaInterna.add(i);
			}
		}
		return listaInterna;
	}

	private ObservableList<InvestimentoFX> filtrarPorCorretora(ObservableList<Corretora> corretoras) {

		// se for todos, devolva a propría lista
		// if (tipos.size() == 1)
		for (Corretora c : corretoras) {
			if (c.getId().equalsIgnoreCase("-1"))
				return listaFiltrada;
		}

		// se não for todos
		ObservableList<InvestimentoFX> listaInterna = FXCollections.observableArrayList();

		for (InvestimentoFX i : listaFiltrada) {
			for (Corretora c : corretoras) {
				if (i.getDadosAdministrativos().getCorretora().getId().equalsIgnoreCase(c.getId()))
					if (!listaInterna.contains(i))
						listaInterna.add(i);
			}
		}
		return listaInterna;
	}

}
