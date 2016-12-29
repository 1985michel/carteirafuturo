package com.carteirafuturo.view;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.util.EstruturaData;
import com.carteirafuturo.util.MainListsAdmin;
import com.carteirafuturo.util.MascaraFinanceira;
import com.carteirafuturo.util.OrdenaListDeInvestimentosPorData;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class TelaInicialController {

	MainApp mainApp;

	MainListsAdmin mainList;
	
	boolean isMenuOpen = false;

	@FXML
	private SplitPane baseSplitPane;
	@FXML
	private AnchorPane menuLateralAnchorPane;
	@FXML
	private TableView<InvestimentoFX> investimentosTableView;
	@FXML
	private TableColumn<InvestimentoFX, String> dataAplicacaoTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> eficienciaTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> valorInvestidoTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> descricaoTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> valorAtualTableColumn;
	@FXML
	private TableColumn<InvestimentoFX, String> LucratividadeTableColumn;

	// Do Resumo
	@FXML
	private Label totalLabel;

	@FXML
	private Label investidosLabel;

	@FXML
	private Label lucroLabel;

	@FXML
	private Label lucroPercentualLabel;

	@FXML
	private Label dataMetaLabel;

	@FXML
	private Label valorMetaLabel;

	@FXML
	private Label acompanhamentoMetaLabel;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.mainList = new MainListsAdmin(main);
		povoarTabela();
		showResumo();
	}

	public void povoarTabela() {
		OrdenaListDeInvestimentosPorData.ordenaInvestimentosPorData(this.mainApp.aGrandeListaDeInvestimentos);
		investimentosTableView.setItems(this.mainApp.aGrandeListaDeInvestimentos);
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	public void initialize() {
		
		
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
				this.mainApp.showInvestimentoOverview(investimentosTableView.getSelectionModel().getSelectedItem(),this);
			}
		});
		// Colocando tooltip
		investimentosTableView.setRowFactory(tv -> new TableRow<InvestimentoFX>() {
			private Tooltip tooltip = new Tooltip();

			@Override
			public void updateItem(InvestimentoFX inves, boolean empty) {
				super.updateItem(inves, empty);
				if (inves == null) {
					setTooltip(null);
				} else {
					tooltip.setText(inves.getDadosAdministrativos().getPlano());
					setTooltip(tooltip);
				}
			}
		});
		eficienciaTableColumn.setCellValueFactory(cellData -> cellData.getValue().eficienciaProperty());

	}

	private void centralizaTableColumn(TableColumn tc) {
		tc.setStyle("-fx-alignment: CENTER;");
	}

	@FXML
	private void showCadastrarTipoDeInvestimento() {
		this.mainApp.showCadastrarTipoDeInvestimento();
	}

	@FXML
	private void showCadastrarInvestidor() {
		this.mainApp.showCadastrarInvestidor();
	}

	@FXML
	private void showCadastrarCorretora() {
		this.mainApp.showCadastrarCorretora();
	}

	@FXML
	private void showCadastrarInvestimento() {
		this.mainApp.showCadastrarInvestimento(this);
	}

	@FXML
	private void showCadastrarMeta() {
		this.mainApp.showCadastrarMeta();
	}

	@FXML
	private void showMetas() {
		this.mainApp.showMetas();
	}

	@FXML
	public void showResumo() {
		totalLabel.setText(MascaraFinanceira.formataMoeda(mainList.getValorAtualTotal()));
		investidosLabel.setText(MascaraFinanceira.formataMoeda(mainList.getValorInvestidoTotal()));
		lucroLabel.setText(MascaraFinanceira.formataMoeda(mainList.getLucroTotal()));
		lucroPercentualLabel.setText("% " + mainList.getLucroPercentualString());
		// dataMetaLabel
		// valorMetaLabel
		// acompanhamentoMetaLabel

	}

	public void atualizarExibicaoDados() {
		this.initialize();
		this.showResumo();
	}

	
	private void hideMenuLateral() {
		//this.menuLateralAnchorPane.setMaxWidth(0);
		//this.menuLateralAnchorPane.setMinWidth(0);
		
		DoubleProperty dDrop = this.baseSplitPane.getDividers().get(0).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(800),dDrop);
		dt.setToValue(.0);
		dt.play();
		isMenuOpen = false;

	}

	
	private void showMenuLateral() {
	//	this.menuLateralAnchorPane.setMaxWidth(150);
	//	this.menuLateralAnchorPane.setMinWidth(150);
		
		DoubleProperty dDrop = this.baseSplitPane.getDividers().get(0).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(800),dDrop);
		dt.setToValue(.2);
		dt.play();
		isMenuOpen = true;
	}
	
	@FXML
	public void animaMenu(){
		if(isMenuOpen) hideMenuLateral();
		else showMenuLateral();
			
	}
	
	

}
