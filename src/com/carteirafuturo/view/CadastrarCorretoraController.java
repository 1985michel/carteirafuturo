package com.carteirafuturo.view;


import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.Corretora;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarCorretoraController {

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Vari�vel que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private TextField nomeTextField;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}

	/**
	 * Inicializa a classe controller. M�todo chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
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
	 * Chamado quando o usu�rio clica ok
	 */
	@FXML
	private void handleOk() {
		String nome = nomeTextField.getText();

		okClicked = true;

		// Criando o nome tipo
		Corretora corretora = new Corretora("1",nome);
		this.mainApp.aGrandeListaDeCorretoras.add(corretora);

		// Colocando a varia��o no db

		dialogStage.close();

	}

	/**
	 * Chamado quando o usu�rio clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
