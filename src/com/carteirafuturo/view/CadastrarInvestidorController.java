package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.InvestidorDAO;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class CadastrarInvestidorController {

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Vari�vel que monitora o retorno do dialog
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
	 * Inicializa a classe controller. M�todo chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
		// centralizando
		investidoresTableView.getColumns().forEach(c -> centralizaTableColumn(c));
		IdTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nomeTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());

		// Detecta o duplo click do mouse e apresenta detalhamento
		investidoresTableView.setOnMousePressed((event) -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
				this.mainApp.showAtualizarEDeletarInvestidor(
						investidoresTableView.getSelectionModel().getSelectedItem(), this);
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
	 * Chamado quando o usu�rio clica ok
	 */
	@FXML
	private void handleOk() {
		String nome = nomeTextField.getText();
		
		// Protegendo da n�o informa��o do nome
		if (nome.length() < 2) {
			Tooltip tooltip = new Tooltip("Informe o nome!");
			nomeTextField.setTooltip(tooltip);
			tooltip.show(dialogStage);
			tooltip.setAutoHide(true);

			return;
		}

		okClicked = true;

		// Criando o nome tipo
		Investidor investidor = new Investidor(nome);
		InvestidorDAO.registrarInvestidor(investidor);
		this.mainApp.aGrandeListaDeInvestidores.add(investidor);

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

	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}

	public void atualizarDadosExibidos() {
		this.initialize();
		
	}

}
