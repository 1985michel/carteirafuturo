package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.Meta;
import com.carteirafuturo.util.CalcularVariacao;
import com.carteirafuturo.util.EstruturaData;
import com.carteirafuturo.util.MainListsAdmin;
import com.carteirafuturo.util.MascaraFinanceira;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	private TableColumn<Meta, Double> acompanhamentoTableColumn;

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
		// centralizando
		metasTableView.getColumns().forEach(c -> centralizaTableColumn(c));
		isAtingidaTableColumn.setCellValueFactory(cellData -> cellData.getValue().isAtingidoProperty());
		dataTableColumn
				.setCellValueFactory(cellData -> EstruturaData.estruturaData(cellData.getValue().dataProperty()));
		valorTableColumn.setCellValueFactory(
				cellData -> MascaraFinanceira.showProperty(cellData.getValue().valorASerAtingidoProperty()));
		// O as object abaixo permite usar o Double na declaração da coluna
		acompanhamentoTableColumn.setCellValueFactory(
				cellData -> statusDaMetaDoubleProperty(cellData.getValue().valorASerAtingidoProperty()).asObject());

		// Colocando o ProgressBar
		Callback<TableColumn<Meta, Double>, TableCell<Meta, Double>> cellFactory = new Callback<TableColumn<Meta, Double>, TableCell<Meta, Double>>() {
			public TableCell call(TableColumn<Meta, Double> p) {
				return new TableCell<Meta, Double>() {

					private ProgressBar pb = new ProgressBar(50);

					// private Text txt = new Text();
					// private HBox hBox = HBoxBuilder.create().children(pb,
					// txt).alignment(Pos.CENTER_LEFT).spacing(5).build();
					@Override
					public void updateItem(Double item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setText(null);
							setGraphic(null);
						} else {
							pb.setProgress(0);
							pb.setProgress(item / 100);
							pb.prefWidthProperty().bind(this.widthProperty());
							// txt.setText("value: " + item);
							// setGraphic(hBox);
							setGraphic(pb);

							setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						}
					}
				};
			}
		};

		acompanhamentoTableColumn.setCellFactory(cellFactory);

		// Colocando tooltip
		metasTableView.setRowFactory(tv -> new TableRow<Meta>() {
			private Tooltip tooltip = new Tooltip();

			@Override
			public void updateItem(Meta m, boolean empty) {
				super.updateItem(m, empty);
				if (m == null) {
					setTooltip(null);
				} else {
					tooltip.setText(m.getDescricao() + "\n\nValor Atual: R$"
							+ MascaraFinanceira.show(mainList.getValorAtualTotal()));
					setTooltip(tooltip);
				}
			}
		});
	}

	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}

	private StringProperty statusDaMeta(DoubleProperty meta) {

		return new SimpleStringProperty(
				CalcularVariacao.calcFaltaParaMeta(mainList.getValorAtualTotal(), meta.get()) + " %");

	}

	private DoubleProperty statusDaMetaDoubleProperty(DoubleProperty meta) {
		return new SimpleDoubleProperty(
				CalcularVariacao.calcFaltaParaMetaDouble(mainList.getValorAtualTotal(), meta.get()));
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
