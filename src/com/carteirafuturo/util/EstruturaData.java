package com.carteirafuturo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EstruturaData {

	static DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static String estruturaData(String data) {

		String[] dataStr = data.split("-");
		int ano = Integer.parseInt(dataStr[0]);
		int mes = Integer.parseInt(dataStr[1]);
		int dia = Integer.parseInt(dataStr[2]);

		LocalDate dataNoFormato = LocalDate.of(ano, mes, dia);

		return myDateFormatter.format(dataNoFormato);

	}
	
	public static LocalDate getLocalDate(String data) {

		String[] dataStr = data.split("-");
		int ano = Integer.parseInt(dataStr[0]);
		int mes = Integer.parseInt(dataStr[1]);
		int dia = Integer.parseInt(dataStr[2]);

		LocalDate dataNoFormato = LocalDate.of(ano, mes, dia);

		return dataNoFormato;

	}
	
	

	public static StringProperty estruturaData(StringProperty dataAtendimentoProperty) {

		String data = dataAtendimentoProperty.getValue();

		String[] dataStr = data.split("-");
		int ano = Integer.parseInt(dataStr[0]);
		int mes = Integer.parseInt(dataStr[1]);
		int dia = Integer.parseInt(dataStr[2]);

		LocalDate dataNoFormato = LocalDate.of(ano, mes, dia);

		StringProperty dataFormatadaProperty = new SimpleStringProperty(myDateFormatter.format(dataNoFormato));
		return dataFormatadaProperty;
	}
	
	public static String estruturaData(LocalDate dataIn) {

		String data = dataIn.toString();

		return EstruturaData.estruturaData(data);
	}
	
	public static long getQtdDias(String data) {
		LocalDate hoje = LocalDate.now();
		LocalDate dataDoInvestimento = EstruturaData.getLocalDate(data);
		return  ChronoUnit.DAYS.between(dataDoInvestimento, hoje);
	}
	
	public static long getQtdMesesFromNow(String data){
		LocalDate hoje = LocalDate.now();
		LocalDate dataPrevista = EstruturaData.getLocalDate(data);
		return ChronoUnit.MONTHS.between(hoje, dataPrevista);
	}
	
	public static long getQtdMeses(String data, String data2){
		LocalDate dataPrevista = EstruturaData.getLocalDate(data);
		LocalDate dataPrevista2 = EstruturaData.getLocalDate(data2);
		return ChronoUnit.MONTHS.between(dataPrevista2, dataPrevista);
	}

}
