package ai.aurora.delivery.acao;

import ai.aurora.core.BlocoExecutor;
import ai.aurora.delivery.entidade.Cliente;
import ai.aurora.delivery.repositorio.ClienteRepositorio;

public class ClienteAcao extends BlocoExecutor {
	
	public void run() {
		
			ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
			
			Cliente cliente = clienteRepositorio.obter(String.valueOf(getBot().obterValor("telefone")));
			
			if (cliente != null) {

				getBot().adicionar("nome", cliente.nome());
				getBot().adicionar("endereco", cliente.endereco());
				
				getBlocoAcionador().setProximoBloco(getBlocosId().get(0));

			} else {
				getBlocoAcionador().setProximoBloco(getBlocosId().get(1));
			}
			
	}

}
