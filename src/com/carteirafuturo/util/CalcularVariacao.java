package com.carteirafuturo.util;

import javafx.beans.property.SimpleStringProperty;

public class CalcularVariacao {

	public static String calc(double valorInicial, double valorFinal) {

		double variacao = ((100 * valorFinal) / valorInicial) - 100;

		// NÃO É DINEHRIO É PORCENTAGEM
		return String.format("%.2f", variacao);

	}
	
	public static String calcFaltaParaMeta(double valorInicial, double valorFinal) {

		double variacao = ((100 * valorFinal) / valorInicial) - 100;
		variacao = 100 - variacao;

		// NÃO É DINEHRIO É PORCENTAGEM
		return String.format("%.2f", variacao);

	}
	
	public static SimpleStringProperty calcProperty(double valorInicial, double valorFinal) {

		double variacao = ((100 * valorFinal) / valorInicial) - 100;

		// NÃO É DINEHRIO É PORCENTAGEM
		return new SimpleStringProperty(String.format("%.2f", variacao));

	}

	

	public static SimpleStringProperty getLucroProperty(String i, String f) {

		double valorI = new Double(i);
		double valorF = new Double(f);
		double lucro = valorF - valorI;
		String lucroString = MascaraFinanceira.show(lucro);
		return new SimpleStringProperty(lucroString);

	}
	
	public static SimpleStringProperty getLucroProperty(double valorAnterior, double valorAtual) {
		
		double lucro = valorAtual - valorAnterior;
		String lucroString = MascaraFinanceira.show(lucro);
		return new SimpleStringProperty(lucroString);

	}

	public static String getLucroPercentualString(double i, double f) {

		double lucro = (100 * f) / i;
		// NÃO É DINEHRIO É PORCENTAGEM
		lucro = lucro - 100;
		return String.format("%.2f", lucro);

	}
}
