package ai.blip.aurora.core;

import java.util.ArrayList;
import java.util.List;

public abstract class BlocoExecutor {
	
	private Bot bot;
	
	private Bloco blocoAcionador;
	
	private List<String> blocosId = new ArrayList<>();
	
	public BlocoExecutor(Bot bot, List<String> blocosId) {
		super();
		this.bot = bot;
		this.blocosId = blocosId;
	}

	public Bloco getBlocoAcionador() {
		return blocoAcionador;
	}

	public void setBlocoAcionador(Bloco blocoAcionador) {
		this.blocoAcionador = blocoAcionador;
	}
	
	public List<String> getBlocosId() {
		return blocosId;
	}

	public void setBlocosId(List<String> blocosId) {
		this.blocosId = blocosId;
	}
	
	public Bot getBot() {
		return bot;
	}

	public abstract void run();

}
