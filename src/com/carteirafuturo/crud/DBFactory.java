package com.carteirafuturo.crud;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DBFactory {

	private List<String> comandosDeCriacao = new ArrayList<>();

	public DBFactory() {
		// String criarTabelaUsuarios = "CREATE TABLE USUARIOS (" + "id INTEGER
		// IDENTITY PRIMARY KEY,"
		// + "LOGIN VARCHAR(50)," + "senha VARCHAR(100)," + ");";

		/*
		String criarTabelaUsuarios = "CREATE TABLE USUARIOS (" + "id INTEGER IDENTITY PRIMARY KEY,"
				+ "LOGIN VARCHAR(50)," + "senha VARCHAR(100)," + ");";

		this.comandosDeCriacao.add(criarTabelaUsuarios);
	*/
		
		String criarTabelaMeta = "CREATE TABLE META (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "DESCRICAO VARCHAR(5000)," + "data VARCHAR(10)," + "valor VARCHAR(50)," + "ISATINGIDO BOOLEAN," + ");";

		this.comandosDeCriacao.add(criarTabelaMeta);
		
		
		String criarTabelaInvestimento = "CREATE TABLE INVESTIMENTO (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "descricao VARCHAR(500)," + "idInvestidor VARCHAR(10)," + "idCorretora VARCHAR(10)," 
				+ "idTipo VARCHAR(10)," + "valor VARCHAR(50)," + "rentabilidadeEsperada VARCHAR(10)," + "custosOperacionais VARCHAR(10),"+
				"data VARCHAR(10), plano VARCHAR(5000),ISRESGATADO BOOLEAN DEFAULT FALSE NOT NULL);";

		this.comandosDeCriacao.add(criarTabelaInvestimento);

		String criarTabelaHistoricoDeRentabilidade = "CREATE TABLE HISTORICODERENTABILIDADE (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "idInvestimento VARCHAR(10)," + "data VARCHAR(10)," + "valor VARCHAR(50)," + ");";

		this.comandosDeCriacao.add(criarTabelaHistoricoDeRentabilidade);

		String criarTabelaTipoDeInvestimento = "CREATE TABLE TIPODEINVESTIMENTO (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "nome VARCHAR(200)" + ");";

		this.comandosDeCriacao.add(criarTabelaTipoDeInvestimento);

		String criarTabelaInvesidor = "CREATE TABLE INVESTIDOR (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "nome VARCHAR(200));";

		this.comandosDeCriacao.add(criarTabelaInvesidor);
		
		String criarTabelaCorretora = "CREATE TABLE CORRETORA (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "nome VARCHAR(200));";

		this.comandosDeCriacao.add(criarTabelaCorretora);

	}

	public boolean criarBancos(CRUD crud) throws ClassNotFoundException, SQLException {

		for (String comando : comandosDeCriacao) {
			crud.getResultSet(comando);
		}
		return true;
	}

}
