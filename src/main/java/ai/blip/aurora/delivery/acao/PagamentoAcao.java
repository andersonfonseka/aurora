package ai.blip.aurora.delivery.acao;

import java.util.List;
import java.util.UUID;

import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.delivery.repositorio.CarrinhoRepositorio;

public class PagamentoAcao extends BlocoExecutor {
	
	private String pedidoConfirmado;
	
	public PagamentoAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
		this.pedidoConfirmado = blocosId.get(0);
	}

	public void run() {
		
			CarrinhoRepositorio carrinhoRepositorio = CarrinhoRepositorio.obterInstancia();
			
			getBot().adicionar("resumo", carrinhoRepositorio.obterDetalhamentoCompra(getBot().getChatId()));
			getBot().adicionar("pix_code", UUID.randomUUID().toString());
			getBot().adicionar("valor_total", carrinhoRepositorio.getProdutosValorTotal(getBot().getChatId()));
		
			getBlocoAcionador().setProximoBloco(this.pedidoConfirmado);
	}

}
