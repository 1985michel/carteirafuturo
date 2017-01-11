package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.TipoDeInvestimentoDAO;
import com.carteirafuturo.model.TipoDeInvestimento;
import com.carteirafuturo.util.MainListsAdmin;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class AtualizarEDeletarTipoDeInvestimentoController {

	TipoDeInvestimento t;

	MainApp mainApp;
	
	String prazoSelecionado = "";

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private Label idTipoDeInvestimentoLabel;

	@FXML
	private TextField nomeTextField;
	
	@FXML
	private ComboBox<String> prazoComboBox;

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

	public void setTipoDeInvestimento(TipoDeInvestimento t) {
		this.t = t;
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
		prazoComboBox.setOnAction((event) -> {
			prazoSelecionado = prazoComboBox.getValue();
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

	public void povoarFormulario() {
		this.idTipoDeInvestimentoLabel.setText(t.getId());
		this.nomeTextField.setText(t.getNome());
		this.prazoComboBox.setValue(t.getPrazo());

		// Se houver algum investimento com o tipo escolhido o botão de deleção
		// será desabilitado e um tooltipo será apresentado
		if (new MainListsAdmin(this.mainApp).temInvestimentoComAqueleTipo(t)) {
			deletarGroupInto.setTooltip(new Tooltip("Há investimentos vinculados a este Tipo de Investimento.\nDeleção bloqueada."));
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
		t.setNome(nome);
		t.setPrazo(prazoSelecionado);

		// Atualizando no banco
		TipoDeInvestimentoDAO.atualizarTipoDeInvestimento(t);

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
		TipoDeInvestimentoDAO.deletarTipoDeInvestimento(t);

		// Deletando da lista
		this.mainApp.aGrandeListaDeTiposDeInvestimento.remove(t);

		dialogStage.close();
	}
	
	public void povoarComboBoxs() {
		prazoComboBox.getItems().addAll(TipoDeInvestimento.prazosObservable);
	}

}
