package ai.aurora.rh.acao;

import ai.aurora.core.Bloco;
import ai.aurora.core.BlocoAcao;
import ai.aurora.core.BlocoExecutor;
import ai.aurora.rh.entidade.Empregado;
import ai.aurora.rh.repositorio.EmpregadoRepositorio;

public class EmpregadoAcao extends BlocoExecutor {
	
	private String dadosEntrada, dadosConfirmacao, blocoRetorno;
	
	public EmpregadoAcao(BlocoAcao blocoAcao) {
		super(blocoAcao);
		
		this.dadosEntrada = getBlocosId().get(0);
		this.dadosConfirmacao = getBlocosId().get(1);
		this.blocoRetorno = getBlocosId().get(2);
		
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
