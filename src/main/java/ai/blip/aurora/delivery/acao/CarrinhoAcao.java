package ai.blip.aurora.delivery.acao;

import java.util.List;

import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.BlocoMenu;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.delivery.entidade.Produto;
import ai.blip.aurora.delivery.repositorio.CarrinhoRepositorio;
import ai.blip.aurora.delivery.repositorio.ProdutoRepositorio;

public class CarrinhoAcao extends BlocoExecutor {
	
	private String menuCheckout;
	
	public CarrinhoAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
		
		this.menuCheckout = blocosId.get(0);
	}

	public void run() {
		
			BlocoMenu blocoMenu = (BlocoMenu) getBot().getBlocosNavegadosPorTipo(BlocoMenu.class);
			
			String resposta = blocoMenu.getResposta();
			
			ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
			CarrinhoRepositorio carrinhoRepositorio = CarrinhoRepositorio.obterInstancia();
			
			Produto produto = produtoRepositorio.obter(resposta);
			
			carrinhoRepositorio.adicionar(getBot().getChatId(), produto);
			
			getBlocoAcionador().setProximoBloco(this.menuCheckout);
	}

}
