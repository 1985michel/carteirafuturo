package testes;

import java.sql.SQLException;

import com.carteirafuturo.MainApp;
import com.carteirafuturo.crud.CRUD;
import com.carteirafuturo.crud.CorretoraDAO;
import com.carteirafuturo.crud.DBFactory;
import com.carteirafuturo.crud.HistoricoDeRentabilidadeDAO;
import com.carteirafuturo.crud.InvestimentoFXDAO;
import com.carteirafuturo.crud.MetaDAO;
import com.carteirafuturo.crud.TipoDeInvestimentoDAO;
import com.carteirafuturo.model.Aplicacao;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.DadosAdministrativos;
import com.carteirafuturo.model.HistoricoDeRentabilidade;
import com.carteirafuturo.model.Investidor;
import com.carteirafuturo.model.InvestimentoFX;
import com.carteirafuturo.model.Meta;
import com.carteirafuturo.model.TipoDeInvestimento;

public class TestesCrud {

	public static void main(String... strings) {
		
		/*
		TipoDeInvestimento tipo = new TipoDeInvestimento("Tesouro Direto");
		TipoDeInvestimentoDAO.registrarTipoDeInvestimento(tipo);
		
		System.out.println(tipo.getId());
		System.out.println(tipo.getNome());
		
		Corretora c = new Corretora("XP");
		CorretoraDAO.registrarCorretora(c);
		System.out.println(c.getId());
		System.out.println(c.getNome());
		
		for (TipoDeInvestimento t : TipoDeInvestimentoDAO.getTodosTipoDeInvestimentoes()) {
			System.out.println(t.getId()+" - "+t.getNome());
		}
		
		for (Corretora co : CorretoraDAO.getTodosCorretoraes()) {
			System.out.println(co.getId()+" - "+co.getNome());
		}
		
		
		
	
		
		DBFactory dbf = new DBFactory();
		try {
			dbf.criarBancos(new CRUD());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		

		/*
		InvestimentoFX i = null;
		MainApp main = new MainApp();
		for (InvestimentoFX j : InvestimentoFXDAO.getTodosInvestimentos(main)) {
			if(j.getId().equalsIgnoreCase("4"))
				i = j;
		}
		
		System.out.println(i.getId());
		Aplicacao api = new Aplicacao("2016-12-26", 99);
		i.setAplicacaoInicial(api);
		DadosAdministrativos adm = new DadosAdministrativos(new TipoDeInvestimento("0", "TD"), "9desc", 0.99, "9plano", new Investidor("0", "m"),
				new Corretora("0", "f"));
		i.setDadosAdministrativos(adm);
		InvestimentoFXDAO.atualizarInvestimento(i);
		*/
				
				
		//Meta m = new Meta("2016-12-28", "descricao da meta 2", 200);
		//MetaDAO.registrarMeta(m);
		
		for (Meta m2 : MetaDAO.getTodasMetas()) {
			System.out.println(m2);
		}
		
		System.out.println(MetaDAO.getMetaPeloId("1"));
		
	}

}
