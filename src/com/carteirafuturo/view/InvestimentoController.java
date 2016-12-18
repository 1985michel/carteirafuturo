package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.InvestimentoFX;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InvestimentoController {

	MainApp mainApp;
	
	public InvestimentoFX investimento;
	
	// Palco desse dialog
	private Stage dialogStage;
	
    @FXML
    public BorderPane areaDeTrabalho;
    
    
    public void setMainApp(MainApp main){
    	this.mainApp = main;
    }
    
    public void setInvestimento(InvestimentoFX i){
    	this.investimento = i;
    }
    
    /**
	 * Define o palco deste dialog. Usado para fecha-lo, por exemplo
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	public void showHistoricoDeRentabilidade(){
		this.mainApp.showHistoricoDeRentabilidade(this.areaDeTrabalho, this.investimento);
	}
	
	@FXML
	public void showApresentacaoLabels(){
		this.mainApp.showApresentacaoLabels(this.areaDeTrabalho, this.investimento);
	}
	
	
    
    

}
