package com.carteirafuturo.view;


import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.CorretoraDAO;
import com.carteirafuturo.model.Corretora;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarCorretoraController {

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private TextField nomeTextField;
    @FXML
    private TableView<Corretora> corretorasTableView;

    @FXML
    private TableColumn<Corretora, String> idTableColumn;

    @FXML
    private TableColumn<Corretora, String> nomeTableColumn;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.corretorasTableView.setItems(this.mainApp.aGrandeListaDeCorretoras);
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
		idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
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
		Corretora corretora = new Corretora(nome);
		CorretoraDAO.registrarCorretora(corretora);
		this.mainApp.aGrandeListaDeCorretoras.add(corretora);

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
