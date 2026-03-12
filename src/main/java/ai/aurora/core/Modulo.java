package ai.aurora.core;

public abstract class Modulo {
	
	private Bot bot;
	
	private Bloco blocoInicial;
	
	private MensagemUtil prop = MensagemUtil.obterInstancia();
	
	public Modulo(Bot bot) {
		super();
		this.bot = bot;
	}
	
	public Bot getBot() {
		return bot;
	}

	public abstract void setBlocoRetorno(Bloco blocoRetorno);

	public Bloco getBlocoInicial() {
		return blocoInicial;
	}

	public void setBlocoInicial(Bloco blocoInicial) {
		this.blocoInicial = blocoInicial;
	}

	public MensagemUtil getProp() {
		return prop;
	}
	
}
