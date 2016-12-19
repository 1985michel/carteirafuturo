package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.InvestidorDAO;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarInvestidorController {

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private TextField nomeTextField;

	@FXML
	private TableView<Investidor> investidoresTableView;

	@FXML
	private TableColumn<Investidor, String> IdTableColumn;

	@FXML
	private TableColumn<Investidor, String> nomeTableColumn;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.investidoresTableView.setItems(this.mainApp.aGrandeListaDeInvestidores);
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
		IdTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nomeTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
	}

	/**
	 * Define o palco deste dialog. Usado para fecha-lo, por exemplo
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Retorna true se o ok for clicado
	 */
	public boolean isOkCLicked() {
		return okClicked;
	}

	/**
	 * Chamado quando o usuário clica ok
	 */
	@FXML
	private void handleOk() {
		String nome = nomeTextField.getText();

		okClicked = true;

		// Criando o nome tipo
		Investidor investidor = new Investidor(nome);
		InvestidorDAO.registrarInvestidor(investidor);
		this.mainApp.aGrandeListaDeInvestidores.add(investidor);
		

		// Colocando a variação no db

		dialogStage.close();
		

	}

	/**
	 * Chamado quando o usuário clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
