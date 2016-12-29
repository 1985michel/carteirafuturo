package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.CorretoraDAO;
import com.carteirafuturo.model.Corretora;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
		corretorasTableView.getColumns().forEach(c -> centralizaTableColumn(c));

		idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nomeTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

		// Detecta o duplo click do mouse e apresenta detalhamento
		corretorasTableView.setOnMousePressed((event) -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
				this.mainApp.showAtualizarEDeletarCorretora(corretorasTableView.getSelectionModel().getSelectedItem(),
						this);
			}
		});
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

		// Protegendo da não informação do nome
		if (nome.length() < 2) {
			Tooltip tooltip = new Tooltip("Informe o nome!");
			nomeTextField.setTooltip(tooltip);
			tooltip.show(dialogStage);
			tooltip.setAutoHide(true);

			return;
		}

		okClicked = true;

		// Criando o nome tipo
		Corretora corretora = new Corretora(nome);
		CorretoraDAO.registrarCorretora(corretora);
		this.mainApp.aGrandeListaDeCorretoras.add(corretora);

		dialogStage.close();

	}

	/**
	 * Chamado quando o usuário clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public void atualizarDadosExibidos() {
		this.initialize();

	}

	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}
}
