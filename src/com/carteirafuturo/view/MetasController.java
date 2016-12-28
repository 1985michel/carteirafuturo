package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.Meta;
import com.carteirafuturo.util.CalcularVariacao;
import com.carteirafuturo.util.EstruturaData;
import com.carteirafuturo.util.MainListsAdmin;
import com.carteirafuturo.util.MascaraFinanceira;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class MetasController {

	MainApp mainApp;

	MainListsAdmin mainList;

	// Palco desse dialog
	private Stage dialogStage;

	// Variável que monitora o retorno do dialog
	private boolean okClicked = false;

	@FXML
	private TableView<Meta> metasTableView;

	@FXML
	private TableColumn<Meta, Boolean> isAtingidaTableColumn;

	@FXML
	private TableColumn<Meta, String> dataTableColumn;

	@FXML
	private TableColumn<Meta, String> valorTableColumn;

	@FXML
	private TableColumn<Meta, String> acompanhamentoTableColumn;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.mainList = new MainListsAdmin(main);
		this.metasTableView.setItems(this.mainApp.aGrandeListaDeMetas);
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	private void initialize() {
		isAtingidaTableColumn.setCellValueFactory(cellData -> cellData.getValue().isAtingidoProperty());
		dataTableColumn
				.setCellValueFactory(cellData -> EstruturaData.estruturaData(cellData.getValue().dataProperty()));
		valorTableColumn.setCellValueFactory(
				cellData -> MascaraFinanceira.showProperty(cellData.getValue().valorASerAtingidoProperty()));
		acompanhamentoTableColumn.setCellValueFactory(cellData -> statusDaMeta(cellData.getValue().valorASerAtingidoProperty()));
		
		// centralizando
		metasTableView.getColumns().forEach(c -> centralizaTableColumn(c));

		// Colocando tooltip
		metasTableView.setRowFactory(tv -> new TableRow<Meta>() {
			private Tooltip tooltip = new Tooltip();

			@Override
			public void updateItem(Meta m, boolean empty) {
				super.updateItem(m, empty);
				if (m == null) {
					setTooltip(null);
				} else {
					tooltip.setText(m.getDescricao());
					setTooltip(tooltip);
				}
			}
		});
	}

	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}

	private StringProperty statusDaMeta(DoubleProperty meta) {
		return new SimpleStringProperty(CalcularVariacao.calcFaltaParaMeta(mainList.getValorAtualTotal(), meta.get())+" %");
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
	
	@FXML
	private void showCadastrarMeta() {
		this.mainApp.showCadastrarMeta();
	}

}
