package ai.blip.aurora.delivery.acao;

import java.util.List;

import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.delivery.entidade.Cliente;
import ai.blip.aurora.delivery.repositorio.ClienteRepositorio;

public class ClienteCadastroAcao extends BlocoExecutor {
	
	private String menuPrincipal;
	
	public ClienteCadastroAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
		
		this.menuPrincipal = blocosId.get(0);
	}

	public void run() {
		
			ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
			
			String telefone = String.valueOf(getBot().obterValor("telefone"));
			String nome = String.valueOf(getBot().obterValor("nome"));
			String endereco = String.valueOf(getBot().obterValor("endereco"));
			
			Cliente cliente = new Cliente(telefone,nome, endereco);
			clienteRepositorio.cadastrar(cliente);
			getBlocoAcionador().setProximoBloco(this.menuPrincipal);
			
	}

}
