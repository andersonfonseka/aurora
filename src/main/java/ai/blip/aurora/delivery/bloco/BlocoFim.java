package ai.blip.aurora.delivery.bloco;

import ai.blip.aurora.core.BlocoEncerramento;
import ai.blip.aurora.core.Bot;

public class BlocoFim extends BlocoEncerramento {

	public BlocoFim(Bot bot) {
		super(bot);
	}

	@Override
	public void build() {
		getBot().setCountBlocoInicial(0);
	}

}
