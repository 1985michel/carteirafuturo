package com.carteirafuturo.crud;

import java.sql.ResultSet;

import com.carteirafuturo.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CorretoraDAO {

	public static void registrarCorretora(Corretora u) {

		int id = 0;

		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud
					.getResultSet("INSERT INTO Corretora (nome) VALUES ('" + u.getNome() + "');CALL IDENTITY();");

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

	public static ObservableList<Corretora> getTodosCorretoraes() {

		int id = 0;
		ObservableList<Corretora> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM Corretora");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				lista.add(new Corretora(idt, resultSet.getString("nome")));
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

	public static void atualizarCorretora(Corretora u) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();

			resultSet = crud
					.getResultSet("UPDATE Corretora SET nome= '" + u.getNome() + "' WHERE id='" + u.getId() + "'");

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

	public static void deletarCorretora(Corretora u) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("DELETE FROM Corretora WHERE id= '" + u.getId() + "'");
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

	public static Corretora getCorretoraPeloId(String id) {

		Corretora i = null;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM Corretora WHERE id= '" + id + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				i = new Corretora(idt, resultSet.getString("nome"));
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
