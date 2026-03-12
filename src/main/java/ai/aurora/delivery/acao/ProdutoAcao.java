package ai.aurora.delivery.acao;

import java.util.List;

import ai.aurora.core.BlocoExecutor;
import ai.aurora.core.BlocoMenu;
import ai.aurora.core.ItemMenu;
import ai.aurora.delivery.entidade.Produto;
import ai.aurora.delivery.repositorio.ProdutoRepositorio;

public class ProdutoAcao extends BlocoExecutor {

	public void run() {
		
			BlocoMenu blocoMenu = (BlocoMenu) getBot().getBlocosNavegadosPorTipo(BlocoMenu.class);
			
			String resposta = blocoMenu.getResposta();
			
			ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
			
			List<Produto> produtos = produtoRepositorio.obterLista(Integer.valueOf(resposta));
			
			BlocoMenu blocoMenuProdutos = (BlocoMenu) getBot().obterBloco("menu.produtos");
			
			blocoMenuProdutos.clear();
			
			for (Produto produto : produtos) {
				blocoMenuProdutos.adicionarItemMenu(new ItemMenu(produto.id(), produto.descricao() + " R$ - " + produto.valor(), "acao.adicionar.carrinho"));
			}

			blocoMenuProdutos.adicionarItemMenu(new ItemMenu("99", "Voltar", "menu.categorias"));
			
			blocoMenuProdutos.build();
			
			getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
	}

}
