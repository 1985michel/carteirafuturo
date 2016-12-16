package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.Aplicacao;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.util.EstruturaData;
import com.carteirafuturo.util.MascaraFinanceira;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ApresentacaoLabelsController {

	MainApp mainApp;	
	
	public InvestimentoFX i;

	@FXML
	private Label tipoLabel;

	@FXML
	private Label investidorLabel;

	@FXML
	private Label corretoraLabel;

	@FXML
	private Label custosOperacionaisLabel;

	@FXML
	private Label rentabilidadeEsperadaLabel;

	@FXML
	private Label dataDoInvestimentoLabel;

	@FXML
	private Label valorInvestidoLabel;

	@FXML
	private Label descricaoLabel;
	
    @FXML
    private Button atualizarCotacaoButton;

	// Palco desse dialog
	private Stage dialogStage;

	
	public void setMainApp(MainApp main) {
		this.mainApp = main;
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
	 * Initialize - é chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {}
	
	
	@FXML
	public void atualizarCotacao(){
		this.mainApp.showAtualizaCotacaoOverview(i);
	}

	
	public void povoarDados(){
		DadosAdministrativos dA = i.getDadosAdministrativos();
		this.descricaoLabel.setText(dA.getDescricao());
		this.tipoLabel.setText(dA.getTipo().getNome());
		this.investidorLabel.setText(dA.getInvestidor().getNome());
		this.corretoraLabel.setText(dA.getCorretora().getNome());
		this.custosOperacionaisLabel.setText("R$ 0,00");
		this.rentabilidadeEsperadaLabel.setText(MascaraFinanceira.show(dA.getRentabilidadeEsperada())+" %");
		
		Aplicacao aI = i.getAplicacaoInicial();
		this.dataDoInvestimentoLabel.setText(EstruturaData.estruturaData(aI.getDataInvestimento()));
		this.valorInvestidoLabel.setText("R$ "+MascaraFinanceira.show(aI.getValorInvestido()));
	}

}
