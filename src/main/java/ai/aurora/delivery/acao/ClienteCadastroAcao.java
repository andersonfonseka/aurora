package ai.aurora.delivery.acao;

import ai.aurora.core.BlocoExecutor;
import ai.aurora.delivery.entidade.Cliente;
import ai.aurora.delivery.repositorio.ClienteRepositorio;

public class ClienteCadastroAcao extends BlocoExecutor {
	
	public void run() {
		
			ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
			
			String telefone = String.valueOf(getBot().obterValor("telefone"));
			String nome = String.valueOf(getBot().obterValor("nome"));
			String endereco = String.valueOf(getBot().obterValor("endereco"));
			
			Cliente cliente = new Cliente(telefone,nome, endereco);
			clienteRepositorio.cadastrar(cliente);
			getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
			
	}

}
