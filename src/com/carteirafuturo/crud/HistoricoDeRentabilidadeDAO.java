package com.carteirafuturo.crud;

import java.sql.ResultSet;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.HistoricoDeRentabilidade;
import com.carteirafuturo.util.MainListsAdmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HistoricoDeRentabilidadeDAO {
	
	public static void gravar(HistoricoDeRentabilidade hR) {

		int id = 0;

		
		//String criarTabelaHistoricoDeRentabilidade = "CREATE TABLE HISTORICODERENTABILIDADE (" + "id INTEGER IDENTITY PRIMARY KEY, "
		//		+ "idInvestimento VARCHAR(10)," + "data VARCHAR(10)," + "valor VARCHAR(50)," + ");";
		
		// Gravando a nova cotação no banco
		ResultSet resultSet = null;
		try {	
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet(
					"INSERT INTO HISTORICODERENTABILIDADE (idInvestimento,valor,data) VALUES ('"
							+ hR.getIdInvestimento() + "','" 
							+ hR.getValor() + "','" 
							+ hR.getData()
							+ "');CALL IDENTITY();");
			if (resultSet.next()) {
				id = resultSet.getInt(1);// obtendo o idretornado CALL
				// IDENTITY();
				hR.setId(id + "");
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
	
	public static ObservableList<HistoricoDeRentabilidade> getTodosHistoricosDeRentabilidade(MainApp main) {

		MainListsAdmin listsAdmin = new MainListsAdmin(main);
		int id = 0;
		ObservableList<HistoricoDeRentabilidade> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM HISTORICODERENTABILIDADE");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				String idInvestimento = resultSet.getString("idInvestimento");
				String data = resultSet.getString("data");
				String valor = resultSet.getString("valor");
								
				lista.add(new HistoricoDeRentabilidade(idt, idInvestimento, data, new Double(valor)));
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

}
