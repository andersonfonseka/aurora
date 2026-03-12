package ai.aurora.pedagogia;

public class AvaliacaoResultado {
	
	private boolean correto;
	
	private String feedback;

	public AvaliacaoResultado(boolean correto, String feedback) {
		this.correto = correto;
		this.feedback = feedback;
	}

	public boolean isCorreto() {
		return correto;
	}

	public void setCorreto(boolean correto) {
		this.correto = correto;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	

}
