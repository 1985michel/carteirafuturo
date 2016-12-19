package com.carteirafuturo.crud;

import java.sql.ResultSet;

import com.carteirafuturo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvestidorDAO {

	public static void registrarInvestidor(Investidor u) {

		int id = 0;

		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("INSERT INTO INVESTIDOR (nome) VALUES ('" + u.getNome() + "');CALL IDENTITY();");

			if (resultSet.next()) {
				id = resultSet.getInt(1);// obtendo o idretornado CALL
				// IDENTITY();
				u.setId(id + "");
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

	public static ObservableList<Investidor> getTodosInvestidores() {

		int id = 0;
		ObservableList<Investidor> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM INVESTIDOR");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				lista.add(new Investidor(idt, resultSet.getString("nome")));
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

	public static void atualizarInvestidor(Investidor u) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();

			resultSet = crud.getResultSet("UPDATE INVESTIDOR SET nome= '" + u.getNome() +"' WHERE id='" + u.getId() + "'");

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

	public static void deletarInvestidor(Investidor u) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("DELETE FROM INVESTIDOR WHERE id= '" + u.getId() + "'");
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

	public static Investidor getInvestidorPeloId(String id) {

		Investidor i = null;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM INVESTIDOR WHERE id= '" + id + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				i = new Investidor(idt, resultSet.getString("nome"));
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

}
