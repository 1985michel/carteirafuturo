package com.carteirafuturo.crud;

import java.sql.ResultSet;

import com.carteirafuturo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TipoDeInvestimentoDAO {

	public static void registrarTipoDeInvestimento(TipoDeInvestimento u) {

		int id = 0;

		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud
					.getResultSet("INSERT INTO TipoDeInvestimento (nome,prazo) VALUES ('" + u.getNome() + "','" + u.getPrazo() + "');CALL IDENTITY();");

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

	public static ObservableList<TipoDeInvestimento> getTodosTipoDeInvestimentoes() {

		int id = 0;
		ObservableList<TipoDeInvestimento> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM TipoDeInvestimento");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				lista.add(new TipoDeInvestimento(idt, resultSet.getString("nome"),resultSet.getString("prazo")));
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

	public static void atualizarTipoDeInvestimento(TipoDeInvestimento u) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();

			resultSet = crud
					.getResultSet("UPDATE TipoDeInvestimento SET nome= '" + u.getNome() + "',prazo= '" + u.getPrazo() + "' WHERE id='" + u.getId() + "'");

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

	public static void deletarTipoDeInvestimento(TipoDeInvestimento u) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("DELETE FROM TipoDeInvestimento WHERE id= '" + u.getId() + "'");
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

	public static TipoDeInvestimento getTipoDeInvestimentoPeloId(String id) {

		TipoDeInvestimento i = null;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM TipoDeInvestimento WHERE id= '" + id + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				i = new TipoDeInvestimento(idt, resultSet.getString("nome"));
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
