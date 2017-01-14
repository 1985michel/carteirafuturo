package com.carteirafuturo.view;

import java.time.LocalDate;
import java.util.Set;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.HistoricoDeRentabilidadeDAO;
import com.carteirafuturo.model.HistoricoDeRentabilidade;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TextFieldMoney;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AtualizaCotacaoAcaoController {
	
	
	MainApp mainApp;
	ApresentacaoLabelsController apLabelCtrl;
	
	

	InvestimentoFX i;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;
	
    @FXML
    private Button okButton;

	@FXML
	private DatePicker dataDatePicker;

	@FXML
	private TextFieldMoney valorTotalTextField;
	
	@FXML
    private TextFieldMoney valorUnitarioTextField;

    @FXML
    private TextField qtdPapeisTextField;
    
    @FXML
    private Accordion accordion;

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
		// setando a data de hoje no local date
		dataDatePicker.setValue(LocalDate.now());
		valorTotalTextField.setOnKeyTyped((event) -> {
			okButton.setDisable(false);
		});
		valorUnitarioTextField.setOnKeyTyped((event) -> {
			okButton.setDisable(false);
		});
		qtdPapeisTextField.setOnKeyTyped((event) -> {
			okButton.setDisable(false);
			qtdPapeisTextField.setStyle("");
		});
		
		accordion.setExpandedPane(accordion.getPanes().get(0));
	}

	/**
	 * Define o palco deste dialog. Usado para fecha-lo, por exemplo
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setInvestimento(InvestimentoFX i) {
		this.i = i;
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
		String valor ="";
		double valorDouble = 0.0;
		double qtd =0;
		
		if(valorTotalTextField.getText().length()>0 && valorUnitarioTextField.getText().length()==0){
			valor = valorTotalTextField.getCleanValue();
			valorDouble = new Double(valor);
		}
		else if(valorTotalTextField.getText().length()==0 && valorUnitarioTextField.getText().length()>0){
			valor = valorUnitarioTextField.getCleanValue();
			try {
				qtd = Double.valueOf(qtdPapeisTextField.getText());
				valorDouble = new Double(valor)*qtd;
			} catch (Exception e) {
				okButton.setDisable(true);
				qtdPapeisTextField.setStyle("-fx-background-color:#FF7B51");
				return;
			}
			
		}
		else{
			okButton.setDisable(true);
			return;
		}
		okClicked = true;
		
		

		HistoricoDeRentabilidade histRent = new HistoricoDeRentabilidade(i.getId(), data, valorDouble);
		// Criando a variação
		i.addListHistoricoDeRentabilidade(histRent);

		// Colocando a variação no db
		HistoricoDeRentabilidadeDAO.gravar(histRent);

		dialogStage.close();

		// Povoar dados na tela de Apresentação por Labels
		apLabelCtrl.povoarDados();

		// Atualizar tabela da tela inicial
		this.mainApp.telaInicialController.atualizarExibicaoDados();

	}

	/**
	 * Chamado quando o usuário clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public void setApresentacaoLabelsController(ApresentacaoLabelsController apCtrl) {
		this.apLabelCtrl = apCtrl;
	}
	
	@FXML
	private void clearUnitario(){	
		valorUnitarioTextField.setText("");
		qtdPapeisTextField.setText("");
		
	}
	
	@FXML
	private void clearTotal(){	
		valorTotalTextField.setText("");
	}
	
	

}