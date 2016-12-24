package com.carteirafuturo.view;

import com.carteirafuturo.crud.InvestimentoFXDAO;
import com.carteirafuturo.model.Aplicacao;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.util.EstruturaData;

import javafx.fxml.FXML;

public class AtualizarInvestimentoController extends CadastrarInvestimentoController {

	InvestimentoFX i;

	@FXML
	public void povoarFormulario(InvestimentoFX i) {
		this.i = i;

		descricaoTextField.setText(i.getDadosAdministrativos().getDescricao());

		valorInvestidorMoneyTextField.setText(String.valueOf(i.getAplicacaoInicial().getValorInvestido()));

		tipoComboBox.setValue(i.getDadosAdministrativos().getTipo());

		investidorComboBox.setValue(i.getDadosAdministrativos().getInvestidor());

		corretoraComboBox.setValue(i.getDadosAdministrativos().getCorretora());

		dataDaAplicacaoDatePicker.setValue(EstruturaData.getLocalDate(i.getAplicacaoInicial().getDataInvestimento()));

		rentabilidadeEsperadaTextField.setText(String.valueOf(i.getDadosAdministrativos().getRentabilidadeEsperada()));

		planoTextArea.setText(i.getDadosAdministrativos().getPlano());
		
		this.investidorSelecionado = i.getDadosAdministrativos().getInvestidor();
		this.corretoraSelecionada = i.getDadosAdministrativos().getCorretora();
		this.tipoSelecionado = i.getDadosAdministrativos().getTipo();

	}

	@Override
	@FXML
	protected void handleOk() {
		String descricao = descricaoTextField.getText();
		String rentabilidadeEsperada = rentabilidadeEsperadaTextField.getText();
		String data = dataDaAplicacaoDatePicker.getValue().toString();
		String valor = valorInvestidorMoneyTextField.getText();
		String plano = planoTextArea.getText();

		DadosAdministrativos dadosAdministrativos = new DadosAdministrativos(tipoSelecionado, descricao,
				new Double(rentabilidadeEsperada), plano, investidorSelecionado, corretoraSelecionada);

		Aplicacao aplicacaoInicial = new Aplicacao(data, new Double(valor));

		// InvestimentoFX investimento = new InvestimentoFX(aplicacaoInicial,
		// dadosAdministrativos);
		i.setAplicacaoInicial(aplicacaoInicial);
		i.setDadosAdministrativos(dadosAdministrativos);
		
		System.out.println("Investidor Selecionado: "+i.getDadosAdministrativos().getInvestidor().getId());
		InvestimentoFXDAO.atualizarInvestimento(i);

		okClicked = true;

	

		dialogStage.close();
		
		//Atualizar apresentação de Labels
		//this.mainApp.showInvestimentoOverview(i);

		// Atualizar tabela da tela inicial
		this.mainApp.telaInicialController.atualizarExibicaoDados();

	}
}
