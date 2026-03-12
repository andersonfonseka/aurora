package ai.aurora.delivery.bloco;

import ai.aurora.core.BlocoEncerramento;
import ai.aurora.core.Bot;

public class BlocoFim extends BlocoEncerramento {

	public BlocoFim(Bot bot) {
		super(bot);
	}

	@Override
	public void build() {
		getBot().setCountBlocoInicial(0);
	}

}
