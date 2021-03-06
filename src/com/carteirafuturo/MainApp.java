package com.carteirafuturo;

import java.io.IOException;

import com.carteirafuturo.crud.CorretoraDAO;
import com.carteirafuturo.crud.HistoricoDeRentabilidadeDAO;
import com.carteirafuturo.crud.InvestidorDAO;
import com.carteirafuturo.crud.InvestimentoFXDAO;
import com.carteirafuturo.crud.MetaDAO;
import com.carteirafuturo.crud.TipoDeInvestimentoDAO;
import com.carteirafuturo.model.Aplicacao;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.EficienciaDeInvestimento;
import com.carteirafuturo.model.HistoricoDeRentabilidade;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.Meta;
import com.carteirafuturo.model.TipoDeInvestimento;
import com.carteirafuturo.view.ApresentacaoLabelsController;
import com.carteirafuturo.view.AtualizaCotacaoAcaoController;
import com.carteirafuturo.view.AtualizaCotacaoController;
import com.carteirafuturo.view.AtualizarEDeletarCorretoraController;
import com.carteirafuturo.view.AtualizarEDeletarInvestidorController;
import com.carteirafuturo.view.AtualizarEDeletarTipoDeInvestimentoController;
import com.carteirafuturo.view.AtualizarInvestimentoController;
import com.carteirafuturo.view.CadastrarCorretoraController;
import com.carteirafuturo.view.CadastrarInvestidorController;
import com.carteirafuturo.view.CadastrarInvestimentoController;
import com.carteirafuturo.view.CadastrarMetaController;
import com.carteirafuturo.view.CadastrarTipoDeInvestimentoController;
import com.carteirafuturo.view.DeletarInvestimentoController;
import com.carteirafuturo.view.HistoricoDeRentabilidadeController;
import com.carteirafuturo.view.InvestimentoController;
import com.carteirafuturo.view.MetasController;
import com.carteirafuturo.view.ResgatarInvestimentoController;
import com.carteirafuturo.view.TelaInicialController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	public ObservableList<InvestimentoFX> aGrandeListaDeInvestimentos = FXCollections.observableArrayList();
	public ObservableList<InvestimentoFX> aGrandeListaDeInvestimentosAtivos = FXCollections.observableArrayList();
	public ObservableList<InvestimentoFX> aGrandeListaDeInvestimentosResgatados = FXCollections.observableArrayList();
	public ObservableList<TipoDeInvestimento> aGrandeListaDeTiposDeInvestimento = FXCollections.observableArrayList();
	public ObservableList<Investidor> aGrandeListaDeInvestidores = FXCollections.observableArrayList();
	public ObservableList<Corretora> aGrandeListaDeCorretoras = FXCollections.observableArrayList();
	public ObservableList<HistoricoDeRentabilidade> aGrandeListaDeHistoricoDeRentabilidade =  FXCollections.observableArrayList();
	public ObservableList<Meta> aGrandeListaDeMetas = FXCollections.observableArrayList();


	private Stage primaryStage;
	private AnchorPane rootLayout;
	public TelaInicialController telaInicialController;
	

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Carteira Futuro");
		
		
		
		carregaListas();
		
		/*
		Aplicacao aplicacao = new Aplicacao("2016-12-15", 50000.00);
		
		//Dados Administrativos
		TipoDeInvestimento tipo = new TipoDeInvestimento("Poupanca");
		Investidor investidor = new Investidor("Miguel");
		Corretora corretora = new Corretora("XP Investimentos");
		DadosAdministrativos adm = new DadosAdministrativos(tipo, "Poupanca do Miguel", 0.9,"Reserva de Emerg�ncia", investidor, corretora);
		InvestimentoFX iFx = new InvestimentoFX(aplicacao, adm);
		iFx.setId("1");
		aGrandeListaDeInvestimentos.add(iFx);
		iFx.addListHistoricoDeRentabilidade(new HistoricoDeRentabilidade("1", "2016-12-16", 50200.00));
		iFx.addListHistoricoDeRentabilidade(new HistoricoDeRentabilidade("1", "2017-12-16", 50300.00));
		aGrandeListaDeCorretoras.add(new Corretora("0","XP"));
		aGrandeListaDeInvestidores.add(new Investidor("0","Michel"));
		aGrandeListaDeTiposDeInvestimento.add(new TipoDeInvestimento("0","Poupan�a"));
		
		for (Corretora c : aGrandeListaDeCorretoras) {
			System.out.println("Corretora: "+c);
		}
		
		for (TipoDeInvestimento t : aGrandeListaDeTiposDeInvestimento) {
			System.out.println(t);
		}

		for (Investidor i : aGrandeListaDeInvestidores) {
			System.out.println(i);
		}
		*/
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
	
	
	public void showInvestimentoOverview(InvestimentoFX i, TelaInicialController telaInicialController) {
		try {

			
			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/InvestimentoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
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

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			

			// Show
			this.showApresentacaoLabels(controller.areaDeTrabalho, i);
			dialogStage.showAndWait();
			telaInicialController.atualizarExibicaoDados();
			

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
	
	public void showAtualizaCotacaoOverview(InvestimentoFX i, ApresentacaoLabelsController apCtrl) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AtualizaCotacaoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			AtualizaCotacaoController controller = loader.getController();
			controller.setMainApp(this);
			controller.setApresentacaoLabelsController(apCtrl);
			

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualiza Cota��o");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//Setando o investimento que vai ser trabalhado
			controller.setInvestimento(i);
			
			//bloqueando recisase
			dialogStage.setResizable(false);

			// Show
			
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAtualizaCotacaoAcaoOverview(InvestimentoFX i, ApresentacaoLabelsController apCtrl) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AtualizaCotacaoAcaoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			AtualizaCotacaoAcaoController controller = loader.getController();
			controller.setMainApp(this);
			controller.setApresentacaoLabelsController(apCtrl);
			

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualiza Cota��o");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//Setando o investimento que vai ser trabalhado
			controller.setInvestimento(i);
			
			//bloqueando recisase
			dialogStage.setResizable(false);

			// Show
			
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showResgatarInvestimentoOverview(InvestimentoFX i) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ResgatarInvestimentoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			ResgatarInvestimentoController controller = loader.getController();
			controller.setMainApp(this);
			
			//controller.setApresentacaoLabelsController(apCtrl);
			

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Resgatar Investimento");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//Setando o investimento que vai ser trabalhado
			controller.setInvestimento(i);
			
			//bloqueando recisase
			dialogStage.setResizable(false);

			// Show
			
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCadastrarTipoDeInvestimento() {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CadastrarTipoDeInvestimentoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			CadastrarTipoDeInvestimentoController controller = loader.getController();
			controller.setMainApp(this);
			controller.povoarComboBoxs();
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Tipo de Investimento");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//bloqueando recisase
			dialogStage.setResizable(false);
			
			// Show
			
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAtualizarEDeletarTipoDeInvestimento(TipoDeInvestimento t, CadastrarTipoDeInvestimentoController cadController) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AtualizarEDeletarTipoDeInvestimento.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			AtualizarEDeletarTipoDeInvestimentoController controller = loader.getController();
			controller.setMainApp(this);
			controller.setTipoDeInvestimento(t);
			controller.povoarComboBoxs();
			controller.povoarFormulario();
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualizar e Deletar Tipo de Investimento");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//bloqueando recisase
			dialogStage.setResizable(false);
			
			// Show
			
			dialogStage.showAndWait();
			
			cadController.atualizarDadosExibidos();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCadastrarInvestidor() {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CadastrarInvestidorOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			CadastrarInvestidorController controller = loader.getController();
			controller.setMainApp(this);
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Investidor");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
		
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//bloqueando recisase
			dialogStage.setResizable(false);
			
			// Show
			
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void showAtualizarEDeletarInvestidor(Investidor iv, CadastrarInvestidorController cadController) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AtualizarEDeletarInvestidor.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			AtualizarEDeletarInvestidorController controller = loader.getController();
			controller.setMainApp(this);
			controller.setInvestidor(iv);
			controller.povoarFormulario();
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualizar e Deletar Investidor");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//bloqueando recisase
			dialogStage.setResizable(false);
			
			// Show
			
			dialogStage.showAndWait();
			
			cadController.atualizarDadosExibidos();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void showCadastrarCorretora() {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CadastrarCorretoraOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			CadastrarCorretoraController controller = loader.getController();
			controller.setMainApp(this);
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Corretora");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//bloqueando recisase
			dialogStage.setResizable(false);
			
			// Show
			
			dialogStage.showAndWait();
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAtualizarEDeletarCorretora(Corretora c, CadastrarCorretoraController cadController) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AtualizarEDeletarCorretora.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			AtualizarEDeletarCorretoraController controller = loader.getController();
			controller.setMainApp(this);
			controller.setCorretora(c);
			controller.povoarFormulario();
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualizar e Deletar Corretora");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//bloqueando recisase
			dialogStage.setResizable(false);
			
			// Show
			
			dialogStage.showAndWait();
			
			cadController.atualizarDadosExibidos();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCadastrarMeta() {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CadastrarMetaOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			CadastrarMetaController controller = loader.getController();
			controller.setMainApp(this);
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Meta");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//bloqueando recisase
			dialogStage.setResizable(false);
			
			// Show			
			dialogStage.showAndWait();
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMetas() {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MetasOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			MetasController controller = loader.getController();
			controller.setMainApp(this);
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Minhas Metas");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			// Show			
			dialogStage.showAndWait();
			
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCadastrarInvestimento(TelaInicialController telaInicialController) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CadastrarInvestimentoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			CadastrarInvestimentoController controller = loader.getController();
			controller.setMainApp(this);
			controller.povoarComboBoxs();
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Cadastrar Investimento");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			//bloqueando recisase
			dialogStage.setResizable(false);
			
			// Show			
			dialogStage.showAndWait();
			telaInicialController.atualizarExibicaoDados();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAtualizarInvestimento(InvestimentoFX i) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AtualizarInvestimentoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			AtualizarInvestimentoController controller = loader.getController();
			controller.setMainApp(this);
			controller.povoarComboBoxs();
			controller.povoarFormulario(i);
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Atualizar Investimento");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			// Show
			
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showDeletarInvestimento(InvestimentoFX i) {
		try {

			// Load o FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/DeletarInvestimentoOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// D� ao controlador acesso ao MainApp
			DeletarInvestimentoController controller = loader.getController();
			controller.setMainApp(this);
			controller.setInvestimento(i);
			controller.povoarDados();
						

			// Criando o dialogStage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Deletar Investimento");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(true);
			// dialogStage.getIcons().add(new
			// Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			//addPersonalStyle(scene);
			dialogStage.setScene(scene);

			// Dando ao controlador poderes sobre seu pr�prio dialogStage
			controller.setDialogStage(dialogStage);
			
			// Show
			
			dialogStage.showAndWait();
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregaListas(){
		this.aGrandeListaDeInvestidores.addAll(InvestidorDAO.getTodosInvestidores());
		this.aGrandeListaDeHistoricoDeRentabilidade.addAll(HistoricoDeRentabilidadeDAO.getTodosHistoricosDeRentabilidade(this));
		this.aGrandeListaDeCorretoras.addAll(CorretoraDAO.getTodosCorretoraes());
		this.aGrandeListaDeTiposDeInvestimento.addAll(TipoDeInvestimentoDAO.getTodosTipoDeInvestimentoes());
		this.aGrandeListaDeInvestimentosAtivos.addAll(InvestimentoFXDAO.getTodosInvestimentosAtivos(this));
		this.aGrandeListaDeInvestimentosResgatados.addAll(InvestimentoFXDAO.getTodosInvestimentosResgatados(this));
		this.aGrandeListaDeInvestimentos.addAll(this.aGrandeListaDeInvestimentosAtivos);
		this.aGrandeListaDeInvestimentos.addAll(this.aGrandeListaDeInvestimentosResgatados);
		this.aGrandeListaDeMetas.addAll(MetaDAO.getTodasMetas());
	
		
		//Atrinbuindo eficiencia
		this.atribuirEficiencia();
	}

	public void atribuirEficiencia() {
		EficienciaDeInvestimento efi = new EficienciaDeInvestimento(aGrandeListaDeInvestimentosAtivos);
		efi.calcularEficiencia();
		
	}
	
	


}
