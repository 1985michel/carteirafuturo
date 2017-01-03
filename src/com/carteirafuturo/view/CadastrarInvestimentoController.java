package com.carteirafuturo.view;

import java.util.ArrayList;
import java.util.List;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.InvestimentoFXDAO;
import com.carteirafuturo.model.Aplicacao;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TipoDeInvestimento;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarInvestimentoController {

	MainApp mainApp;

	TipoDeInvestimento tipoSelecionado;
	Investidor investidorSelecionado;
	Corretora corretoraSelecionada;

	// Palco desse dialog
	protected Stage dialogStage;

	// Variável que monitora o retorno do dialog
	protected boolean okClicked = false;

	@FXML
	protected TextField descricaoTextField;

	@FXML
	protected TextField valorInvestidorMoneyTextField;

	@FXML
	protected ComboBox<TipoDeInvestimento> tipoComboBox;

	@FXML
	protected ComboBox<Investidor> investidorComboBox;

	@FXML
	protected ComboBox<Corretora> corretoraComboBox;

	@FXML
	protected DatePicker dataDaAplicacaoDatePicker;

	@FXML
	protected TextField rentabilidadeEsperadaTextField;
	
	@FXML
	protected TextArea planoTextArea;

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
	protected void initialize() {

		// Povoando ComboBox
		
		
		tipoComboBox.setOnAction((event) -> {
			tipoSelecionado = tipoComboBox.getValue();
		});

		// Povoando ComboBox
		
		investidorComboBox.setOnAction((event) -> {
			investidorSelecionado = investidorComboBox.getValue();
		});
		
		// Povoando ComboBox
		
		corretoraComboBox.setOnAction((event) -> {
			corretoraSelecionada = corretoraComboBox.getValue();
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
	
	public void povoarComboBoxs(){
		for (TipoDeInvestimento ti : this.mainApp.aGrandeListaDeTiposDeInvestimento) {
			tipoComboBox.getItems().add(ti);
		}
		for (Investidor investidor : this.mainApp.aGrandeListaDeInvestidores) {
			investidorComboBox.getItems().add(investidor);
		}
		for (Corretora corretora : this.mainApp.aGrandeListaDeCorretoras) {
			corretoraComboBox.getItems().add(corretora);
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
	protected void handleOk() {
		String descricao = descricaoTextField.getText();
		String rentabilidadeEsperada = rentabilidadeEsperadaTextField.getText();
		String data = dataDaAplicacaoDatePicker.getValue().toString();
		String valor = valorInvestidorMoneyTextField.getText();
		String plano = planoTextArea.getText();
		
		DadosAdministrativos dadosAdministrativos = new DadosAdministrativos(
				tipoSelecionado, descricao,new Double(rentabilidadeEsperada), plano, investidorSelecionado, corretoraSelecionada);
		
		Aplicacao aplicacaoInicial = new Aplicacao(data,new Double(valor));
		
		InvestimentoFX investimento = new InvestimentoFX(aplicacaoInicial, dadosAdministrativos);
		InvestimentoFXDAO.investir(investimento);
		this.mainApp.aGrandeListaDeInvestimentosAtivos.add(investimento);
		
		//Recalculando a eficiência da carteira
		this.mainApp.atribuirEficiencia();
		

		okClicked = true;

		// Criando o nome tipo
		// Investimento i = new InvestimentoFX(aplicacaoInicial,
		// dadosAdministrativos);
		// this.mainApp.aGrandeListaDeInvestimentos.add(i);

		// Colocando a variação no db

		dialogStage.close();

	}

	/**
	 * Chamado quando o usuário clica Cancel.
	 */
	@FXML
	protected void handleCancel() {
		dialogStage.close();
	}
	
	@FXML
	public void showCadastrarInvestidor(){
		this.mainApp.showCadastrarInvestidor();
	}
	
	@FXML
	public void showCadastrarCorretora(){
		this.mainApp.showCadastrarCorretora();
	}
	
	@FXML
	public void showCadastrarTipoDeInvestimento(){
		this.mainApp.showCadastrarTipoDeInvestimento();
	}

}
