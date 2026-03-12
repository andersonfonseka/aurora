package ai.aurora.core;

import java.util.ArrayList;
import java.util.List;

public class BlocoAcao extends Bloco {

	private boolean executarAcao = true;
	
	private String classe;
	
	private List<String> blocosId = new ArrayList<>();
	
	private BlocoExecutor blocoExecutor;
	
	private Bloco blocoAcionador;
	
	public BlocoAcao(Bot bot) {
		super(bot);
	}
	
	public void setBlocoExecutor(BlocoExecutor blocoExecutor) {
		this.blocoExecutor = blocoExecutor;
	}

	public void execute(BlocoExecutor blocoExecutor) {
		this.blocoExecutor = blocoExecutor;
		this.blocoExecutor.setBlocoAcionador(this);
		
	}
	
	public void execute() {
		if (this.executarAcao) {
			blocoExecutor.run();
		}
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public void adicionarBlocoId(String blocoId) {
		this.blocosId.add(blocoId);
	}

	public List<String> getBlocosId() {
		return blocosId;
	}

	public void setBlocoAcionador(Bloco blocoAcionador) {
		this.blocoAcionador = blocoAcionador;
	}

	public Bloco getBlocoAcionador() {
		return blocoAcionador;
	}
	
}
