package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.util.EstruturaData;
import com.carteirafuturo.util.MascaraFinanceira;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TelaInicialController {

	MainApp mainApp;
	

	@FXML
	private TableView<InvestimentoFX> investimentosTableView;
	@FXML
	private TableColumn<InvestimentoFX, String> dataAplicacaoTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> valorInvestidoTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> descricaoTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> valorAtualTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> LucratividadeTableColumn;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		povoarTabela();
	}

	public void povoarTabela() {
		investimentosTableView.setItems(this.mainApp.aGrandeListaDeInvestimentos);
	}

	/**
	 * Inicializa a classe controller. M�todo chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
		dataAplicacaoTableColumn.setCellValueFactory(cellData -> EstruturaData
				.estruturaData(cellData.getValue().getAplicacaoInicial().dataInvestimentoProperty()));
		valorInvestidoTableColumn.setCellValueFactory(cellData -> MascaraFinanceira
				.showProperty(cellData.getValue().getAplicacaoInicial().valorInvestidoProperty()));
		descricaoTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().getDadosAdministrativos().descricaoProperty());
		valorAtualTableColumn.setCellValueFactory(
				cellData -> MascaraFinanceira.showProperty(cellData.getValue().valorAtualStringProperty()));
		LucratividadeTableColumn.setCellValueFactory(cellData -> cellData.getValue().lucratividadePercentualProperty());

		// centralizando
		investimentosTableView.getColumns().forEach(c -> centralizaTableColumn(c));
		// Detecta o duplo click do mouse e apresenta detalhamento
		investimentosTableView.setOnMousePressed((event) -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
				this.mainApp.showInvestimentoOverview(investimentosTableView.getSelectionModel().getSelectedItem());
			}
		});

	}

	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}
	
 
	@FXML
	private void showCadastrarTipoDeInvestimento(){
		this.mainApp.showCadastrarTipoDeInvestimento();
	}
	
	@FXML
	private void showCadastrarInvestidor(){
		this.mainApp.showCadastrarInvestidor();
	}
	
	@FXML
	private void showCadastrarCorretora(){
		this.mainApp.showCadastrarCorretora();
	}
	
	@FXML
	private void showCadastrarInvestimento(){
		this.mainApp.showCadastrarInvestimento();
	}

}
