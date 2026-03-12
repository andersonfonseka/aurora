package ai.aurora.delivery.acao;

import java.util.UUID;

import ai.aurora.core.BlocoExecutor;
import ai.aurora.delivery.repositorio.CarrinhoRepositorio;

public class PagamentoAcao extends BlocoExecutor {

	public void run() {
		
			CarrinhoRepositorio carrinhoRepositorio = CarrinhoRepositorio.obterInstancia();
			
			getBot().adicionar("resumo", carrinhoRepositorio.obterDetalhamentoCompra(getBot().getChatId()));
			getBot().adicionar("pix_code", UUID.randomUUID().toString());
			getBot().adicionar("valor_total", carrinhoRepositorio.getProdutosValorTotal(getBot().getChatId()));
		
			getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
	}

}
