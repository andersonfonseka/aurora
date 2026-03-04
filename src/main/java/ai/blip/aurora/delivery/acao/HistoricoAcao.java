package ai.blip.aurora.delivery.acao;

import java.util.List;

import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.Bot;

public class HistoricoAcao extends BlocoExecutor {
	
	private String exibirHistorico, historicoVazio;
	
	public HistoricoAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);
		
		this.exibirHistorico = blocosId.get(0); 
		this.historicoVazio = blocosId.get(1);
	}

	public void run() {
			getBlocoAcionador().setProximoBloco(this.exibirHistorico);
			getBlocoAcionador().setProximoBloco(this.historicoVazio);
	}

}
