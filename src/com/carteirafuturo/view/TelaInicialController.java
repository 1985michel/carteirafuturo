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

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		investimentosTableView.setItems(main.aGrandeListaDeInvestimentos);
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
		dataAplicacaoTableColumn.setCellValueFactory(cellData -> EstruturaData
				.estruturaData(cellData.getValue().getAplicacaoInicial().dataInvestimentoProperty()));
		valorInvestidoTableColumn.setCellValueFactory(cellData -> MascaraFinanceira
				.showProperty(cellData.getValue().getAplicacaoInicial().valorInvestidoProperty()));

		//centralizando
		investimentosTableView.getColumns().forEach(c -> centralizaTableColumn(c));
		
		
		// Detecta o duplo click do mouse e apresenta o alert perguntando se
				// quer atender aquele cliente.
				// Caso ok, o cliente é carregado no formulário
		investimentosTableView.setOnMousePressed((event) -> {
					if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
						
							// Obtem o id do atendimento
							//String idCli = atendimentosTableView.getSelectionModel().getSelectedItem().getIdCliente();
							//String idAte = atendimentosTableView.getSelectionModel().getSelectedItem().getIdAtendimento();
							// Passa o id para o controller do AtendendoCliente
							//this.mainApp.getAtendendoClienteController().ConsultarClientePeloId(idCli);
							//this.mainApp.getAtendendoClienteController().ConsultarAtendimentoPeloId(idAte);
							
							this.mainApp.showInvestimentoOverview(investimentosTableView.getSelectionModel().getSelectedItem());
						}
					
				});

	}

	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}

}
