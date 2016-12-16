package com.carteirafuturo.model;

import java.time.LocalDate;
import java.util.HashMap;

public class Investimento {

	// Dados Administrativos
	int id;
	TipoDeInvestimento tipo;
	String descricao;
	String investidor;
	String corretora;

	// Sobre os custos
	double impostoDeRenda;
	double tarifas;

	// dados no momento da aplicação
	LocalDate dataInvestimento;
	double valorInvestido;
	double rentabilidadeEsperada;

	// dados no momento presente
	double valorAtual;
	double rentabilidadeReal;

	// Sobre o historio de rentabilidade
	HashMap<LocalDate, Double> dataValorHistoricoDeRentabilidade;

	// Sobre o futuro - projeções
	HashMap<LocalDate, Double> dataValorFuturo;

	// Sobre Resgates
	HashMap<LocalDate, Double> dataValorDoResgate;

	// Sobre aplicações complementares
	HashMap<LocalDate, Double> dataValorAplicacao;

}
