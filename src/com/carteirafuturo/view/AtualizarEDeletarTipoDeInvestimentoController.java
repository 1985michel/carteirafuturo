package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.TipoDeInvestimentoDAO;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AtualizarEDeletarTipoDeInvestimentoController {
	
	TipoDeInvestimento t;
	
	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Vari�vel que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private Label idTipoDeInvestimentoLabel;

	@FXML
	private TextField nomeTextField;
	
	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	public void setTipoDeInvestimento(TipoDeInvestimento t){
		this.t= t;
	}

	/**
	 * Inicializa a classe controller. M�todo chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {}

	/**
	 * Define o palco deste dialog. Usado para fecha-lo, por exemplo
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void povoarFormulario(){
		this.idTipoDeInvestimentoLabel.setText(t.getId());
		this.nomeTextField.setText(t.getNome());
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

		//Atualizando
		t.setNome(nome);
		

		// Atualizando no banco
		TipoDeInvestimentoDAO.atualizarTipoDeInvestimento(t);
		
		dialogStage.close();
	

	}

	/**
	 * Chamado quando o usu�rio clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	@FXML
	private void deleta(){
		
		//deltando do banco
		TipoDeInvestimentoDAO.deletarTipoDeInvestimento(t);
		
		//Deletando da lista
		this.mainApp.aGrandeListaDeTiposDeInvestimento.remove(t);
		
		dialogStage.close();
	}


}
