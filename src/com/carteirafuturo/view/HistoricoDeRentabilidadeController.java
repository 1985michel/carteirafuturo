package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.HistoricoDeRentabilidade;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.util.CalcularVariacao;
import com.carteirafuturo.util.EstruturaData;
import com.carteirafuturo.util.MascaraFinanceira;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class HistoricoDeRentabilidadeController {
	
	MainApp mainApp;	
	
	public InvestimentoFX i;

	@FXML
	private TableView<HistoricoDeRentabilidade> variacoesTableView;

	@FXML
	private TableColumn<HistoricoDeRentabilidade, String> dataTableColumn;

	@FXML
	private TableColumn<HistoricoDeRentabilidade, String> valorTableColumn;

	@FXML
	private TableColumn<HistoricoDeRentabilidade, String> variacaoTableColumn;

    @FXML
    private TableColumn<HistoricoDeRentabilidade, String> lucroTotalTableColumn;

	@FXML
	private Label descricaoLabel;
	
	// Palco desse dialog
	private Stage dialogStage;

	
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		//Povoando a tabela
		variacoesTableView.setItems(i.getListHistoricoDeRentabilidade());
		//Povoando a descricao
		this.descricaoLabel.setText(i.getDadosAdministrativos().getDescricao());
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
	private void initialize(){
		dataTableColumn.setCellValueFactory(cellData -> EstruturaData
				.estruturaData(cellData.getValue().dataProperty()));
		valorTableColumn.setCellValueFactory(cellData -> MascaraFinanceira
				.showProperty(cellData.getValue().valorProperty()));
		variacaoTableColumn.setCellValueFactory(
				cellData ->CalcularVariacao.calcProperty(i.getAplicacaoInicial().getValorInvestido(), cellData.getValue().getValor()));
				
		lucroTotalTableColumn.setCellValueFactory(
				cellData -> CalcularVariacao.getLucroProperty(i.getAplicacaoInicial().getValorInvestido(), cellData.getValue().getValor()));

		//centralizando
		variacoesTableView.getColumns().forEach(c -> centralizaTableColumn(c));
		
		
	}
	
	
	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}

}
