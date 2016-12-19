package testes;

import com.carteirafuturo.crud.CorretoraDAO;
import com.carteirafuturo.crud.TipoDeInvestimentoDAO;
import com.carteirafuturo.model.Corretora;
import com.carteirafuturo.model.TipoDeInvestimento;

public class TestesCrud {

	public static void main(String... strings) {
		
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
		
		
		
	
		/*
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
	}

}
