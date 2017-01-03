package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.InvestimentoFXDAO;
import com.carteirafuturo.crud.MetaDAO;
import com.carteirafuturo.model.Aplicacao;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.Meta;
import com.carteirafuturo.util.EstruturaData;
import com.carteirafuturo.util.MainListsAdmin;
import com.carteirafuturo.util.MascaraFinanceira;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeletarInvestimentoController {

	InvestimentoFX i;

	Meta meta;

	MainApp mainApp;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private Label descricaoLabel;
	
    @FXML
    private Label tipoDeInvestimentoLabel;

	@FXML
	private Label dataLabel;

	@FXML
	private Label valorInvestidoLabel;

	@FXML
	private Label investidorLabel;

	@FXML
	private Label corretoraLabel;

	@FXML
	private Label valorAtualLabel;
	
	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
	}
	
	public void setInvestimento(InvestimentoFX i){
		this.i = i;
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
		
		InvestimentoFXDAO.deletarInvestimento(i);
		

		this.mainApp.aGrandeListaDeInvestimentosAtivos.remove(i);
		
		
		okClicked = true;
		
		dialogStage.close();
		

	}

	/**
	 * Chamado quando o usuário clica Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	public void povoarDados() {
		DadosAdministrativos dA = i.getDadosAdministrativos();
		
		this.descricaoLabel.setText(dA.getDescricao());
		this.tipoDeInvestimentoLabel.setText(dA.getTipo().getNome());
		this.investidorLabel.setText(dA.getInvestidor().getNome());
		this.corretoraLabel.setText(dA.getCorretora().getNome());
		
		//this.planoLabel.setText(i.getDadosAdministrativos().getPlano());

		Aplicacao aI = i.getAplicacaoInicial();
		this.dataLabel.setText(EstruturaData.estruturaData(aI.getDataInvestimento()));
		this.valorInvestidoLabel.setText("R$ " + MascaraFinanceira.show(aI.getValorInvestido()));

		// Historico de rentabilidade
		this.valorAtualLabel.setText("R$ " + MascaraFinanceira.show(String.valueOf(i.getValorAtual())));
		//this.variacaoLabel.setText(i.getLucratividade()+"%");

	}

	

}
