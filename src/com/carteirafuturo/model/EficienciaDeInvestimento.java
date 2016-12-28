package com.carteirafuturo.model;

import java.util.List;

import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.util.EstruturaData;

public class EficienciaDeInvestimento {
	
	
	private List<InvestimentoFX>lista;
	public long periodoMaisLongo;
	
	public EficienciaDeInvestimento(List<InvestimentoFX> lista){
		this.lista = lista;
		setaPeriodoMaisLongo();
	}
	
	public void calcularEficiencia(){
		for (InvestimentoFX i : lista) {
			i.setEficiencia(getEficiencia(i));
		}		
	}
	
	private void setaPeriodoMaisLongo() {
		long p = 0l;
		if(lista!=null && lista.size()>0)
			p = EstruturaData.getQtdDias(lista.get(0).getAplicacaoInicial().getDataInvestimento());

		for (InvestimentoFX i : lista) {
			long dias = EstruturaData.getQtdDias(i.getAplicacaoInicial().getDataInvestimento());
			if(dias>p)
				p = dias;
		}		
		periodoMaisLongo = p;		
	}

	/*
	 * 
	 * */
	public String getEficiencia(InvestimentoFX i){
		
		//Primeiro obtenho a quantidade de dias da aplicação
		long tempoEmDias = EstruturaData.getQtdDias(i.getAplicacaoInicial().getDataInvestimento());
		
		//System.out.println("Periodo mais longo: "+periodoMaisLongo);
		//System.out.println("Tempo aplicado: "+tempoEmDias);
		
		
		//Depois vamos projetar a lucratividade no período mais longo
		 
		double var = new Double((i.getLucratividade()).replace(',', '.'));
		//System.out.println("Variação: "+var);
		
		
		double resultado = (var*periodoMaisLongo)/tempoEmDias;
		//double resultado = 10;
		//System.out.println("Resultado: "+resultado);		
		return String.format("%.2f", resultado);
	}

	

}
