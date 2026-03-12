package ai.aurora.delivery.acao;

import ai.aurora.core.BlocoAcao;
import ai.aurora.core.BlocoExecutor;
import ai.aurora.core.BlocoMenu;
import ai.aurora.delivery.entidade.Produto;
import ai.aurora.delivery.repositorio.CarrinhoRepositorio;
import ai.aurora.delivery.repositorio.ProdutoRepositorio;

public class CarrinhoAcao extends BlocoExecutor {

	public void run() {
		
			BlocoMenu blocoMenu = (BlocoMenu) getBot().getBlocosNavegadosPorTipo(BlocoMenu.class);
			
			String resposta = blocoMenu.getResposta();
			
			ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio();
			CarrinhoRepositorio carrinhoRepositorio = CarrinhoRepositorio.obterInstancia();
			
			Produto produto = produtoRepositorio.obter(resposta);
			
			carrinhoRepositorio.adicionar(getBot().getChatId(), produto);
			
			getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
	}

}
