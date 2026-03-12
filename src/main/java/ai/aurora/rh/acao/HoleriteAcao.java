package ai.aurora.rh.acao;

import java.util.List;

import ai.aurora.core.Bloco;
import ai.aurora.core.BlocoAcao;
import ai.aurora.core.BlocoExecutor;
import ai.aurora.rh.entidade.Holerite;
import ai.aurora.rh.repositorio.HoleriteRepositorio;

public class HoleriteAcao extends BlocoExecutor {
	
	private String holeriteSucesso;
	
	public HoleriteAcao(BlocoAcao blocoAcao) {
		super(blocoAcao);
		this.holeriteSucesso = getBlocosId().get(0);
	}

	public void run() {
		
			HoleriteRepositorio holeriteRepositorio = new HoleriteRepositorio();
			
			List<Holerite> list = holeriteRepositorio.obter(String.valueOf(getBot().obterValor("cpf")));
			
			StringBuilder sb = new StringBuilder();
			
			if (list == null) {
				Bloco bloco = getBot().obterBloco(holeriteSucesso);
				bloco.setPergunta(getBot().getProp().obter("holeriteAcao.erro"));
			} else {
				for (Holerite holerite : list) {
					sb.append(holerite);
				}
				getBot().adicionar("resultado", sb.toString());
			}
			getBlocoAcionador().setProximoBloco(this.holeriteSucesso);
	}

}
