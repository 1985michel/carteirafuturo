package com.carteirafuturo.view;

import java.time.LocalDate;
import java.util.Set;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TextFieldMoney;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AtualizaCotacaoController {

	MainApp mainApp;
	
	InvestimentoFX i;

	// Palco desse dialog
	private Stage dialogStage;

	// Vari�vel que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private DatePicker dataDatePicker;

	@FXML
	private TextFieldMoney valorTextField;

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
		// setando a data de hoje no local date
		dataDatePicker.setValue(LocalDate.now());
	}

	/**
	 * Define o palco deste dialog. Usado para fecha-lo, por exemplo
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setInvestimento(InvestimentoFX i){
		this.i = i;
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
		String data = dataDatePicker.getValue().toString();
		String valor = valorTextField.getCleanValue();

		okClicked = true;

		// Criando a varia��o
		i.getHistoricoDeRentabilidade().addMapHistoricoDeRentabilidade(data, new Double(valor));

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