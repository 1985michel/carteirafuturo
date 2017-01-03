package com.carteirafuturo.view;

import java.util.ArrayList;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.Meta;
import com.carteirafuturo.model.TipoDeInvestimento;
import com.carteirafuturo.util.EstruturaData;
import com.carteirafuturo.util.MainListsAdmin;
import com.carteirafuturo.util.MascaraFinanceira;
import com.carteirafuturo.util.OrdenaListDeInvestimentosPorData;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
	boolean isMenuFiltrosOpen = false;

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

	@FXML
	private AnchorPane menuFiltrosDeSelecaoAnchorPane;

	@FXML
	private ComboBox<String> statusDoInvestimentoComboBox;

	@FXML
	private ComboBox<Investidor> investidorComboBox;

	@FXML
	private ComboBox<TipoDeInvestimento> tipoDeInvestimentoComboBox;

	@FXML
	private ComboBox<Corretora> corretoraComboBox;

	@FXML
	private CheckBox marcarMelhorEPiorEficienciaCheckBox;

	/**
	 * Ligando ao main
	 */
	public void setMainApp(MainApp main) {
		this.mainApp = main;
		this.mainList = new MainListsAdmin(main);
		povoarTabela();
		showResumo();
		hideMenuFiltros();
		hideMenuLateral();
		povoarComboBoxsDoMenuDeItens();
	}

	public void povoarTabela() {
		OrdenaListDeInvestimentosPorData.ordenaInvestimentosPorData(this.mainApp.aGrandeListaDeInvestimentosAtivos);
		investimentosTableView.setItems(this.mainApp.aGrandeListaDeInvestimentosAtivos);
	}

	/**
	 * Inicializa a classe controller. Método chamado ao carregar o fxml
	 */
	@FXML
	public void initialize() {

		// centralizando
		investimentosTableView.getColumns().forEach(c -> centralizaTableColumn(c));

		dataAplicacaoTableColumn.setCellValueFactory(cellData -> EstruturaData
				.estruturaData(cellData.getValue().getAplicacaoInicial().dataInvestimentoProperty()));
		valorInvestidoTableColumn.setCellValueFactory(cellData -> MascaraFinanceira
				.showProperty(cellData.getValue().getAplicacaoInicial().valorInvestidoProperty()));
		descricaoTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().getDadosAdministrativos().descricaoProperty());
		valorAtualTableColumn.setCellValueFactory(
				cellData -> MascaraFinanceira.showProperty(cellData.getValue().valorAtualStringProperty()));
		LucratividadeTableColumn.setCellValueFactory(cellData -> cellData.getValue().lucratividadePercentualProperty());

		// Detecta o duplo click do mouse e apresenta detalhamento
		investimentosTableView.setOnMousePressed((event) -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
				this.mainApp.showInvestimentoOverview(investimentosTableView.getSelectionModel().getSelectedItem(),
						this);
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
		// this.menuLateralAnchorPane.setMaxWidth(0);
		// this.menuLateralAnchorPane.setMinWidth(0);

		DoubleProperty dDrop = this.baseSplitPane.getDividers().get(0).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(800), dDrop);
		dt.setToValue(0);
		dt.play();
		isMenuOpen = false;


	}

	private void showMenuLateral() {
		// this.menuLateralAnchorPane.setMaxWidth(150);
		// this.menuLateralAnchorPane.setMinWidth(150);

		DoubleProperty dDrop = this.baseSplitPane.getDividers().get(0).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(800), dDrop);
		dt.setToValue(.2);
		dt.play();
		isMenuOpen = true;
		
	}

	@FXML
	public void animaMenu() {
		if (isMenuOpen)
			hideMenuLateral();
		else
			showMenuLateral();
	}
	
	
	private void hideMenuFiltros() {

		DoubleProperty dDrop = this.baseSplitPane.getDividers().get(1).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(800), dDrop);
		dt.setToValue(1.0);
		dt.play();
		
		isMenuFiltrosOpen = false;

	}

	private void showMenuFiltros() {

		DoubleProperty dDrop = this.baseSplitPane.getDividers().get(1).positionProperty();
		DoubleTransition dt = new DoubleTransition(Duration.millis(800), dDrop);
		dt.setToValue(.5);
		dt.play();
		
		isMenuFiltrosOpen = true;
	}
	
	@FXML
	public void animaMenuFiltrosDeExibicao() {
		if (isMenuFiltrosOpen)
			hideMenuFiltros();
		else
			showMenuFiltros();
	}
	
	private void povoarComboBoxsDoMenuDeItens(){
		
		ObservableList<Investidor> investidorList = FXCollections.observableArrayList();
		investidorList.add(new Investidor("-1","Todos"));
		investidorList.addAll(this.mainApp.aGrandeListaDeInvestidores);
		this.investidorComboBox.setItems(investidorList);
		
		ObservableList<TipoDeInvestimento> tipoDeInvestimentoList = FXCollections.observableArrayList();
		tipoDeInvestimentoList.add(new TipoDeInvestimento("-1","Todos"));
		tipoDeInvestimentoList.addAll(this.mainApp.aGrandeListaDeTiposDeInvestimento);
		this.tipoDeInvestimentoComboBox.setItems(tipoDeInvestimentoList);
		
		ObservableList<Corretora> CorretoraList = FXCollections.observableArrayList();
		CorretoraList.add(new Corretora("-1","Todas"));
		CorretoraList.addAll(this.mainApp.aGrandeListaDeCorretoras);
		this.corretoraComboBox.setItems(CorretoraList);
		
		ObservableList<String> statusDeInvestimentos = FXCollections.observableArrayList();
		String[] status = {"Todos","Ativos","Resgatados"};
		statusDeInvestimentos.addAll(status);
		this.statusDoInvestimentoComboBox.setItems(statusDeInvestimentos);
		
	}
	
	private void aplicarFiltro(){
		
	}

}
