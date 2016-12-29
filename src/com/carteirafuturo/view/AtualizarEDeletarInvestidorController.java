package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.InvestidorDAO;
import com.carteirafuturo.model.Investidor;

import com.carteirafuturo.util.MainListsAdmin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class AtualizarEDeletarInvestidorController {

	Investidor iv;

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private Label idInvestidorLabel;

	@FXML
	private TextField nomeTextField;

	@FXML
	private Button deletarButton;

	@FXML
	private Label deletarGroupInto;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;

	}

	public void setInvestidor(Investidor iv) {
		this.iv = iv;
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

	public void povoarFormulario() {
		this.idInvestidorLabel.setText(iv.getId());
		this.nomeTextField.setText(iv.getNome());

		// Se houver algum investimento com o tipo escolhido o botão de deleção
		// será desabilitado e um tooltipo será apresentado
		if (new MainListsAdmin(this.mainApp).temInvestimentoComAqueleInvestidor(iv)) {
			deletarGroupInto.setTooltip(new Tooltip("Há investimentos vinculados a este Investidor.\nDeleção bloqueada."));
			this.deletarButton.setDisable(true);
		}else{
			deletarGroupInto.toBack();
		}
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

		// Atualizando
		iv.setNome(nome);

		// Atualizando no banco
		InvestidorDAO.atualizarInvestidor(iv);

		dialogStage.close();

	}

	/**
	 * Chamado quando o usuário clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	@FXML
	private void deleta() {

		// deltando do banco
		InvestidorDAO.deletarInvestidor(iv);

		// Deletando da lista
		this.mainApp.aGrandeListaDeInvestidores.remove(iv);

		dialogStage.close();
	}
}
