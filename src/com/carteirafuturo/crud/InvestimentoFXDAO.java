package com.carteirafuturo.crud;


import java.sql.ResultSet;

import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvestimentoFXDAO {

	public static void investir(InvestimentoFX i) {

		int id = 0;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {
			
			/**
			 * String criarTabelaInvestimento = "CREATE TABLE INVESTIMENTO (" + "id INTEGER IDENTITY PRIMARY KEY, "
				+ "descricao VARCHAR(200)," + "idInvestidor VARCHAR(10)," + "idCorretora VARCHAR(10)," 
				+ "idTipo VARCHAR(10)," + "valor VARCHAR(50)," + "rentabilidadeEsperada VARCHAR(10)," + "custosOperacionais VARCHAR(10),"+ "data VARCHAR(200)," 
				+ ");";
			 * *
			 */

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet(
					"INSERT INTO INVESTIMENTO (descricao,valor,data,idtipoinvestimento,idInvestidor,idcorretora,rentabilidadeEsperada) VALUES ('"
							+ i.getDadosAdministrativos().getDescricao() + "','" + i.getAplicacaoInicial().getValorInvestido() + "','" 
							+ i.getAplicacaoInicial().getDataInvestimento() + "','"
							+ i.getDadosAdministrativos().getTipo().getId() 
							+ "','" + i.getDadosAdministrativos().getInvestidor().getId()
							+ "','" + i.getDadosAdministrativos().getCorretora().getId()
							+ "','" + i.getDadosAdministrativos().getRentabilidadeEsperada()
							+ "');CALL IDENTITY();");

			if (resultSet.next()) {
				id = resultSet.getInt(1);// obtendo o idretornado CALL
				// IDENTITY();
				i.setId(id + "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static ObservableList<InvestimentoFX> getTodosInvestimentos() {

		int id = 0;
		ObservableList<InvestimentoFX> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM INVESTIMENTO");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				TipoDeInvestimento tipo = new TipoDeInvestimento(id, nome)
				DadosAdministrativos dA = new DadosAdministrativos(tipo, descricao, rentabilidadeEsperada, investidor, corretora)
				
				/*
				lista.add(new InvestimentoFX(idt, resultSet.getString("nome"), resultSet.getString("valor"),
						resultSet.getString("data"), resultSet.getString("plano"),
						resultSet.getString("idtipoinvestimento"), resultSet.getString("idInvestidor")));
						*/
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lista;

	}

	public static ObservableList<Investimento> getInvestimentosPorInvestidor(String idInvestidor) {

		int id = 0;
		ObservableList<Investimento> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM INVESTIMENTO WHERE IDINVESTIDOR = '" + idInvestidor + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				lista.add(new Investimento(idt, resultSet.getString("nome"), resultSet.getString("valor"),
						resultSet.getString("data"), resultSet.getString("plano"),
						resultSet.getString("idtipoinvestimento"), resultSet.getString("idInvestidor")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lista;

	}

	public static Investimento getInvestimentoPeloId(String id) {

		Investimento i = null;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM INVESTIMENTO WHERE id= '" + id + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				i = new Investimento(idt, resultSet.getString("nome"), resultSet.getString("valor"),
						resultSet.getString("data"), resultSet.getString("plano"),
						resultSet.getString("idtipoinvestimento"), resultSet.getString("idInvestidor"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return i;

	}

	public static void atualizarInvestimento(Investimento i) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();

			resultSet = crud.getResultSet("UPDATE INVESTIMENTO SET nome= '" + i.getNome() + "', valor= '" + i.getValor()
					+ "', data= '" + i.getData() + "', plano= '" + i.getPlano() + "', idtipoinvestimento= '"
					+ i.getTipoInvestimento().getId() + "', idinvestidor= '" + i.getInvestidor().getId()
					+ "' WHERE id='" + i.getId() + "'");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void deletarInvestimento(Investimento i) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("DELETE FROM INVESTIMENTO WHERE id= '" + i.getId() + "'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}