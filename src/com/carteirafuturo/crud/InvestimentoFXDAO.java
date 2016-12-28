package com.carteirafuturo.crud;


import java.sql.ResultSet;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.Aplicacao;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.HistoricoDeRentabilidade;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TipoDeInvestimento;
import com.carteirafuturo.util.MainListsAdmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvestimentoFXDAO {

	public static void investir(InvestimentoFX i) {

		int id = 0;

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {
		
			CRUD crud = new CRUD();
			resultSet = crud.getResultSet(
					"INSERT INTO INVESTIMENTO (descricao,valor,data,idTipo,idInvestidor,idcorretora,rentabilidadeEsperada,plano) VALUES ('"
							+ i.getDadosAdministrativos().getDescricao() + "','" + i.getAplicacaoInicial().getValorInvestido() + "','" 
							+ i.getAplicacaoInicial().getDataInvestimento() + "','"
							+ i.getDadosAdministrativos().getTipo().getId() 
							+ "','" + i.getDadosAdministrativos().getInvestidor().getId()
							+ "','" + i.getDadosAdministrativos().getCorretora().getId()
							+ "','" + i.getDadosAdministrativos().getRentabilidadeEsperada()
							+ "','" + i.getDadosAdministrativos().getPlano()
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
	
	public static void atualizarInvestimento(InvestimentoFX i) {
		
		/*
		 * 		String criarTabelaInvestimento = "CREATE TABLE INVESTIMENTO (" + "id INTEGER IDENTITY PRIMARY KEY, "
			+ "descricao VARCHAR(500)," + "idInvestidor VARCHAR(10)," + "idCorretora VARCHAR(10)," 
			+ "idTipo VARCHAR(10)," + "valor VARCHAR(50)," + "rentabilidadeEsperada VARCHAR(10)," + "custosOperacionais VARCHAR(10),"+
			"data VARCHAR(10), plano VARCHAR(5000));";
		 * 
		 * 
		 */

		ResultSet resultSet = null;
		try {
			CRUD crud = new CRUD();

			resultSet = crud.getResultSet("UPDATE INVESTIMENTO SET descricao= '" + i.getDadosAdministrativos().getDescricao() 
					+ "', idInvestidor= '" + i.getDadosAdministrativos().getInvestidor().getId()
					+ "', idCorretora= '" + i.getDadosAdministrativos().getCorretora().getId()
					+ "', idTipo= '" + i.getDadosAdministrativos().getTipo().getId()
					+ "', valor= '" + i.getAplicacaoInicial().getValorInvestido()
					+ "', rentabilidadeEsperada= '" + i.getDadosAdministrativos().getRentabilidadeEsperada()
					+ "', custosOperacionais= '" + i.getDadosAdministrativos().getCustosOperacionais()
					+ "', data= '" + i.getAplicacaoInicial().getDataInvestimento()
					+ "', plano= '" + i.getDadosAdministrativos().getPlano()
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

	
	public static ObservableList<InvestimentoFX> getTodosInvestimentos(MainApp main) {

		MainListsAdmin listsAdmin = new MainListsAdmin(main);
		int id = 0;
		ObservableList<InvestimentoFX> lista = FXCollections.observableArrayList();

		// Gravando o cliente ao banco
		ResultSet resultSet = null;
		try {

			CRUD crud = new CRUD();
			resultSet = crud.getResultSet("SELECT * FROM INVESTIMENTO");

			while (resultSet.next()) {
				String idt = resultSet.getInt("id") + "";
				TipoDeInvestimento tipo = listsAdmin.getTipoDeInvestimentoById(resultSet.getString("idTipo"));
				Corretora corretora = listsAdmin.getCorretoraById(resultSet.getString("idCorretora"));
				Investidor investidor = listsAdmin.getInvestidorById(resultSet.getString("idInvestidor"));
				
				String descricao = resultSet.getString("descricao");
				double valor = new Double(resultSet.getString("valor"));
				double rentabilidadeEsperada = new Double(resultSet.getString("rentabilidadeEsperada"));
				String plano = resultSet.getString("plano");
				String data = resultSet.getString("data");
				
				DadosAdministrativos dA = new DadosAdministrativos(tipo, descricao, rentabilidadeEsperada, plano, investidor, corretora);
				Aplicacao aI = new Aplicacao(data, valor);
				
				lista.add(new InvestimentoFX(idt,aI,dA,listsAdmin.getTodosHistoricosDeRentabilidadePorInvestimentoFXId(idt)));
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

	/*
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

	*/


	
	public static void deletarInvestimento(InvestimentoFX i) {

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
