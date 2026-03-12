package ai.aurora.delivery.acao;

import ai.aurora.core.BlocoExecutor;

public class HistoricoAcao extends BlocoExecutor {

	public void run() {
			getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
			getBlocoAcionador().setProximoBloco(getBlocosId().get(1));
	}

}
