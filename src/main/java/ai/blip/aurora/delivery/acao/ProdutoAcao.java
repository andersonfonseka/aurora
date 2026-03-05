package ai.blip.aurora.delivery.acao;

import java.util.List;

import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.BlocoMenu;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.core.ItemMenu;
import ai.blip.aurora.delivery.entidade.Produto;
import ai.blip.aurora.delivery.repositorio.ProdutoRepositorio;

public class ProdutoAcao extends BlocoExecutor {
	
	private String menuCheckout;
	
	public ProdutoAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
		
		this.menuCheckout = blocosId.get(0);
	}

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
			
			getBlocoAcionador().setProximoBloco(this.menuCheckout);
	}

}
