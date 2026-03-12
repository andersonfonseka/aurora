package ai.aurora.delivery.acao;

import java.util.List;

import ai.aurora.core.BlocoAcao;
import ai.aurora.core.BlocoExecutor;
import ai.aurora.core.BlocoMenu;
import ai.aurora.core.ItemMenu;
import ai.aurora.delivery.entidade.Categoria;
import ai.aurora.delivery.repositorio.CategoriaRepositorio;

public class CategoriaAcao extends BlocoExecutor {
	
	public void run() {
		
			BlocoMenu blocoMenu = (BlocoMenu) getBot().getBlocosNavegadosPorTipo(BlocoMenu.class);
			
			String resposta = blocoMenu.getResposta();
			
			CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();
			
			List<Categoria> categorias = categoriaRepositorio.obterLista(Integer.valueOf(resposta));
			
			BlocoMenu blocoMenuCategorias = (BlocoMenu) getBot().obterBloco("menu.categorias");
			
			blocoMenuCategorias.clear();
			
			for (Categoria categoria : categorias) {
				blocoMenuCategorias.adicionarItemMenu(new ItemMenu(categoria.id(), categoria.descricao(), "acao.produtos"));
			}

			blocoMenuCategorias.adicionarItemMenu(new ItemMenu("99", "Voltar", "menu.principal"));
			
			blocoMenuCategorias.build();
			
			getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
	}

}
