package ai.blip.aurora.rh.acao;

import java.util.List;

import ai.blip.aurora.core.Bloco;
import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.rh.entidade.Empregado;
import ai.blip.aurora.rh.repositorio.EmpregadoRepositorio;

public class EmpregadoAcao extends BlocoExecutor {
	
	private String dadosEntrada, dadosConfirmacao, blocoRetorno;
	
	public EmpregadoAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
		
		this.dadosEntrada = blocosId.get(0);
		this.dadosConfirmacao = blocosId.get(1);
		this.blocoRetorno = blocosId.get(2);
		
	}

	public void run() {
		if (String.valueOf(getBot().obterValor("dados_confirmados")).trim().equalsIgnoreCase("Sim")) {
			
			EmpregadoRepositorio clienteRepositorio = new EmpregadoRepositorio();
			Empregado empregado = clienteRepositorio.obter(String.valueOf(getBot().obterValor("cpf")));
			
			if (null != empregado) {
				getBot().adicionar("nome", String.valueOf(empregado.getNome()));
				getBot().adicionar("salario", String.valueOf(empregado.getSalario()));
				getBot().adicionar("dataAdmissao", empregado.getDataAdmissao());
				
				getBlocoAcionador().setProximoBloco(this.blocoRetorno);

			} else {
				Bloco bloco = getBot().obterBloco(dadosEntrada);
  				bloco.setPergunta(getBot().getProp().obter("empregadoAcao.erro"));
				getBlocoAcionador().setProximoBloco(this.blocoRetorno);
			}
		} else {
			getBlocoAcionador().setProximoBloco(this.dadosConfirmacao);
		}
	}

}
