package com.carteirafuturo.util;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FiltrosManager {

	public ObservableList<InvestimentoFX> listaFiltrada = FXCollections.observableArrayList();
	MainApp mainApp;
	
	public FiltrosManager(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	public ObservableList<InvestimentoFX> filtrar(TipoDeInvestimento tipo, Corretora corretora, Investidor investidor){
		
		
		return listaFiltrada;
	}
	
	private ObservableList<InvestimentoFX> filtrarPorTipoDeInvestimento(TipoDeInvestimento tipo,ObservableList<InvestimentoFX> lista ){
		
		//se for todos, devolva a propría lista
		if(tipo.getId().equalsIgnoreCase("-1"))
			return lista;
		
		//se não for todos
		ObservableList<InvestimentoFX> listaInterna = FXCollections.observableArrayList();
		return listaInterna;
	}
	
	
	

}
