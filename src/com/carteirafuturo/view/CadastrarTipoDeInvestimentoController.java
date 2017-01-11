package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.TipoDeInvestimentoDAO;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class CadastrarTipoDeInvestimentoController {

	MainApp mainApp;

	String prazoSelecionado = "";

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private TextField nomeTextField;

	@FXML
	private ComboBox<String> prazoComboBox;

	@FXML
	private TableView<TipoDeInvestimento> tipoDeInvestimentoTableView;

	@FXML
	private TableColumn<TipoDeInvestimento, String> idTableColumn;

	@FXML
	private TableColumn<TipoDeInvestimento, String> nomeTableColumn;

	@FXML
	private TableColumn<TipoDeInvestimento, String> prazoTableColumn;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.tipoDeInvestimentoTableView.setItems(this.mainApp.aGrandeListaDeTiposDeInvestimento);
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {

		prazoComboBox.setOnAction((event) -> {
			prazoSelecionado = prazoComboBox.getValue();
		});

		centralizaTableColumn(idTableColumn);
		idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nomeTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		prazoTableColumn.setCellValueFactory(cellData -> cellData.getValue().prazoProperty());
		prazoTableColumn.setStyle("-fx-alignment: CENTER;");

		// Detecta o duplo click do mouse e apresenta detalhamento
		tipoDeInvestimentoTableView.setOnMousePressed((event) -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
				this.mainApp.showAtualizarEDeletarTipoDeInvestimento(
						tipoDeInvestimentoTableView.getSelectionModel().getSelectedItem(), this);
			}
		});
	}

	public void atualizarDadosExibidos() {
		this.initialize();
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
		int prazoInt = 0;
		for (int i = 0; i < TipoDeInvestimento.prazosObservable.size(); i++) {
			if (TipoDeInvestimento.prazosObservable.get(i).equalsIgnoreCase(prazoSelecionado))
				prazoInt = i;
		}

		TipoDeInvestimento tipo = new TipoDeInvestimento(nome, prazoInt);
		TipoDeInvestimentoDAO.registrarTipoDeInvestimento(tipo);
		this.mainApp.aGrandeListaDeTiposDeInvestimento.add(tipo);

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

	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}

	public void povoarComboBoxs() {
		prazoComboBox.getItems().addAll(TipoDeInvestimento.prazosObservable);
	}

}
