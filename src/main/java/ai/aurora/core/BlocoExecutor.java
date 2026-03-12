package ai.aurora.core;

import java.util.List;

public abstract class BlocoExecutor {
	
	private Bot bot;
	
	private BlocoAcao blocoAcionador;
	
	public BlocoExecutor() {
		super();
	}
	
	public BlocoExecutor(BlocoAcao blocoAcao) {
		super();
		this.bot = blocoAcao.getBot();
	}

	public Bloco getBlocoAcionador() {
		return blocoAcionador;
	}

	public void setBlocoAcionador(BlocoAcao blocoAcionador) {
		this.blocoAcionador = blocoAcionador;
	}
	
	public List<String> getBlocosId() {
		return this.blocoAcionador.getBlocosId();
	}
	
	public Bot getBot() {
		return this.blocoAcionador.getBot();
	}

	public abstract void run();

}
