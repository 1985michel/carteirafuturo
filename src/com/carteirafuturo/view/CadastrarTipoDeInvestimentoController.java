package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.TipoDeInvestimentoDAO;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarTipoDeInvestimentoController {

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private TextField nomeTextField;
    @FXML
    private TableView<TipoDeInvestimento> tipoDeInvestimentoTableView;

    @FXML
    private TableColumn<TipoDeInvestimento, String> idTableColumn;

    @FXML
    private TableColumn<TipoDeInvestimento, String> nomeTableColumn;
	
	
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
		idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nomeTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		centralizaTableColumn(idTableColumn);
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
		TipoDeInvestimento tipo = new TipoDeInvestimento(nome);
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
	

	
	

}
