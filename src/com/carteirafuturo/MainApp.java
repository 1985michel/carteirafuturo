package com.carteirafuturo;

import java.io.IOException;

import com.carteirafuturo.model.Aplicacao;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.HistoricoDeRentabilidade;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.TipoDeInvestimento;
import com.carteirafuturo.view.ApresentacaoLabelsController;
import com.carteirafuturo.view.AtualizaCotacaoController;
import com.carteirafuturo.view.HistoricoDeRentabilidadeController;
import com.carteirafuturo.view.InvestimentoController;
import com.carteirafuturo.view.TelaInicialController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	public ObservableList<InvestimentoFX> aGrandeListaDeInvestimentos = FXCollections.observableArrayList();

	private Stage primaryStage;
	private AnchorPane rootLayout;
	public TelaInicialController telaInicialController;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Carteira Futuro");
		
		Aplicacao aplicacao = new Aplicacao("2016-12-15", 50000.00);
		
		//Dados Administrativos
		TipoDeInvestimento tipo = new TipoDeInvestimento("Poupanca");
		Investidor investidor = new Investidor("Miguel");
		Corretora corretora = new Corretora("XP Investimentos");
		DadosAdministrativos adm = new DadosAdministrativos(tipo, "Poupanca do Miguel", 0.9, investidor, corretora);
		InvestimentoFX iFx = new InvestimentoFX(aplicacao, adm);
		iFx.setId("1");
		aGrandeListaDeInvestimentos.add(iFx);
		iFx.addListHistoricoDeRentabilidade(new HistoricoDeRentabilidade("1", "2016-12-16", 50200.00));
		iFx.addListHistoricoDeRentabilidade(new HistoricoDeRentabilidade("1", "2017-12-16", 50300.00));

		
		

		initRootLayout();

	}

	/**
	 * Retorna o palco principal.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Inicializa o root layout (layout base).
	 */
	public void initRootLayout() {
		try {
			// Carrega o root layout do arquivo fxml.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/TelaInicial.fxml"));
			rootLayout = (AnchorPane) loader.load();
			
			TelaInicialController controller = loader.getController();
			controller.setMainApp(this);
			this.telaInicialController = controller;

			// Mostra a scene (cena) contendo o root layout.
			Scene scene = new Scene(rootLayout);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void showInvestimentoOverview(InvestimentoFX i) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/InvestimentoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Dá ao controlador acesso ao MainApp
			InvestimentoController controller = loader.getController();
			controller.setMainApp(this);
			//Setando o investimento a ser trabalhado
			controller.setInvestimento(i);

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Detalhes do Investimento");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu próprio dialogStage
			controller.setDialogStage(dialogStage);
			
			

			// Show
			this.showApresentacaoLabels(controller.areaDeTrabalho, i);
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void showApresentacaoLabels(BorderPane areaDeTrabalhoBorderPane, InvestimentoFX i) {
		try {
			// Carrega o person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ApresentacaoLabelsOverview.fxml"));
			AnchorPane cadInvOverView = (AnchorPane) loader.load();

			// Atribuindo o controller e o mainApp
			ApresentacaoLabelsController controller = loader.getController();
			controller.setMainApp(this);
			controller.setInvestimento(i);

			// Define o person overview dentro do root layout.
			areaDeTrabalhoBorderPane.setCenter(cadInvOverView);
			controller.povoarDados();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showHistoricoDeRentabilidade(BorderPane areaDeTrabalhoBorderPane, InvestimentoFX i) {
		try {
			// Carrega o person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/HistoricoDeRentabilidadeOverview.fxml"));
			AnchorPane cadInvOverView = (AnchorPane) loader.load();

			// Atribuindo o controller e o mainApp
			HistoricoDeRentabilidadeController controller = loader.getController();
			
			controller.setInvestimento(i);
			controller.setMainApp(this);

			// Define o person overview dentro do root layout.
			areaDeTrabalhoBorderPane.setCenter(cadInvOverView);			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showAtualizaCotacaoOverview(InvestimentoFX i) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AtualizaCotacaoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Dá ao controlador acesso ao MainApp
			AtualizaCotacaoController controller = loader.getController();
			controller.setMainApp(this);
			

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualiza Cotação");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu próprio dialogStage
			controller.setDialogStage(dialogStage);
			
			//Setando o investimento que vai ser trabalhado
			controller.setInvestimento(i);

			// Show
			
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
