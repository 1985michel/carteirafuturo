package com.carteirafuturo.util;

import java.time.LocalDate;

import com.carteirafuturo.model.InvestimentoFX;

import javafx.collections.ObservableList;

public class OrdenaListDeInvestimentosPorData {

	/** Início dos métodos para ordenar os Investimentos por data */
	public static void ordenaInvestimentosPorData(ObservableList<InvestimentoFX> list) {
		list.sort((o1, o2) -> comparaDatas(geraData(o1.getAplicacaoInicial().getDataInvestimento()),
				geraData(o2.getAplicacaoInicial().getDataInvestimento())));
	}

	private static LocalDate geraData(String data) {
		// System.out.println("data: " + data);
		if (data != null && data.length() == 10) {

			String[] estru = data.split("-");
			int ano = Integer.parseInt(estru[0]);
			int mes = Integer.parseInt(estru[1]);
			int dia = Integer.parseInt(estru[2]);
			LocalDate date = LocalDate.of(ano, mes, dia);
			return date;
		} else
			return null;

	}

	private static int comparaDatas(LocalDate data1, LocalDate data2) {
		if (data1.isEqual(data2))
			return 0;
		else if (data1.isBefore(data2))
			return -1;
		else
			return 1;
	}

	/** Fim dos métodos para odenar os investimentos por data */

}
