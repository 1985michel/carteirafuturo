package com.carteirafuturo.util;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.TipoDeInvestimento;

public class MainListsAdmin {
	
	MainApp main;
	public MainListsAdmin(MainApp main){
		this.main = main;
	}
	
	public TipoDeInvestimento getTipoDeInvestimentoById(String id){
		
		for (TipoDeInvestimento tipo : main.aGrandeListaDeTiposDeInvestimento) {
			if(tipo.getId().equalsIgnoreCase(id))
				return tipo;
		}		
		return null;		
	}
	
	public Investidor getInvestidorById(String id){
		for (Investidor i : main.aGrandeListaDeInvestidores) {
			if(i.getId().equalsIgnoreCase(id))
				return i;
		}
		return null;		
	}
	
	public Corretora getCorretoraById(String id){
		for (Corretora c : main.aGrandeListaDeCorretoras) {
			if(c.getId().equalsIgnoreCase(id))
				return c;
		}
		return null;		
	}

}
