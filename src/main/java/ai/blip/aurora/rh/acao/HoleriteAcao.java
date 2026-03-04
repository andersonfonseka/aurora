package ai.blip.aurora.rh.acao;

import java.util.List;

import ai.blip.aurora.core.Bloco;
import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.rh.entidade.Holerite;
import ai.blip.aurora.rh.repositorio.HoleriteRepositorio;

public class HoleriteAcao extends BlocoExecutor {
	
	private String holeriteSucesso;
	
	public HoleriteAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
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
