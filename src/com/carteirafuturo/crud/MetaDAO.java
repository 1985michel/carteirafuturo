package com.carteirafuturo.crud;

import java.sql.ResultSet;

import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.Meta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MetaDAO {

	public static void registrarMeta(Meta m) {

		int id = 0;

		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("INSERT INTO meta (descricao,valor,data,isatingido) VALUES ('"
					+ m.getDescricao() 
					+ "','" +m.getValorASerAtingido() 
					+ "','" +m.getData() 
					+ "','" +m.isIsAtingido() + "');CALL IDENTITY();");

			if (resultSet.next()) {
				id = resultSet.getInt(1);// obtendo o idretornado CALL
				// IDENTITY();
				m.setId(id + "");
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
	
	public static ObservableList<Meta> getTodasMetas() {

		int id = 0;
		ObservableList<Meta> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM Meta");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				lista.add(new Meta(idt,resultSet.getString("data"), resultSet.getString("descricao"),new Double(resultSet.getString("valor")),
						resultSet.getBoolean("ISATINGIDO")));
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
	
	public static void deletarMeta(Meta m) {

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("DELETE FROM META WHERE id= '" + m.getId() + "'");
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
	
	public static Meta getMetaPeloId(String id) {

		Meta m = null;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM meta WHERE id= '" + id + "'");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				m = new Meta(idt,
						resultSet.getString("DATA"),
						resultSet.getString("descricao"),
						new Double(resultSet.getString("valor")),
						resultSet.getBoolean("ISATINGIDO"));
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

		return m;

	}

}
