package com.carteirafuturo.view;

import java.time.LocalDate;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.InvestidorDAO;
import com.carteirafuturo.crud.MetaDAO;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.Meta;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarMetaController {
	
	Meta meta;
	
	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private DatePicker dataDatePicker;

	@FXML
	private TextField valorMoneyTextField;

	@FXML
	private TextArea descriacaoTextArea;

	@FXML
	private CheckBox isMetaAtingidaCheckBox;
	

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
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
	 * Chamado quando o usuário clica ok
	 */
	@FXML
	private void handleOk() {
		String data = dataDatePicker.getValue().toString();
		String descricao = descriacaoTextArea.getText();
		String valor = valorMoneyTextField.getText();
		boolean isAtingido = isMetaAtingidaCheckBox.isArmed();
		
		okClicked = true;

		// Criando a meta
		Meta m = new Meta("",data, descricao, new Double(valor),isAtingido);				
		MetaDAO.registrarMeta(m);
		this.mainApp.aGrandeListaDeMetas.add(m);

		
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
