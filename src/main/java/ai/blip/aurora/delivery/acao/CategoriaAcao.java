package ai.blip.aurora.delivery.acao;

import java.util.List;

import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.BlocoMenu;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.core.ItemMenu;
import ai.blip.aurora.delivery.entidade.Categoria;
import ai.blip.aurora.delivery.repositorio.CategoriaRepositorio;

public class CategoriaAcao extends BlocoExecutor {
	
	private String menuProdutos;
	
	public CategoriaAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
		
		this.menuProdutos = blocosId.get(0);
	}

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
			
			getBlocoAcionador().setProximoBloco(this.menuProdutos);
	}

}
