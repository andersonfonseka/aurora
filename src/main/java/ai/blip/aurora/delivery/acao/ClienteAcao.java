package ai.blip.aurora.delivery.acao;

import java.util.List;

import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.delivery.entidade.Cliente;
import ai.blip.aurora.delivery.repositorio.ClienteRepositorio;

public class ClienteAcao extends BlocoExecutor {
	
	private String menuPrincipal;
	
	public ClienteAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
		
		this.menuPrincipal = blocosId.get(0);
	}

	public void run() {
		
			ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
			
			Cliente cliente = clienteRepositorio.obter(String.valueOf(getBot().obterValor("telefone")));
			
			getBot().adicionar("nome", cliente.nome());
			getBot().adicionar("endereco", cliente.endereco());
			
			getBlocoAcionador().setProximoBloco(this.menuPrincipal);
	}

}
