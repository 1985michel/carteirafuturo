package com.carteirafuturo.util;

import java.text.NumberFormat;
import java.util.Locale;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MascaraFinanceira {

	public static String show(Double valor) {
		Locale locale = new Locale("pt", "BR");
		NumberFormat nf = NumberFormat.getInstance(locale);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);

		return nf.format(valor);
	}

	public static String show(String valor) {
		double v = new Double(valor);
		return show(v);
	}

	public static StringProperty showProperty(StringProperty valor) {
		String v = valor.getValue();
		String vFormatado = show(v);
		return new SimpleStringProperty(vFormatado);
	}
	
	public static StringProperty showProperty(DoubleProperty valor) {
		double v = valor.get();
		Locale locale = new Locale("pt", "BR");
		NumberFormat nf = NumberFormat.getInstance(locale);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);

		return new SimpleStringProperty(nf.format(v));
	}
	

	
	

	// Método para formatar um valor
	public static String formataMoeda(double vlr) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(vlr);
	}
}
