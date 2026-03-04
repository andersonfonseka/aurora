package ai.blip.aurora.core;

public class Variavel {

	private String nome;
	
	private boolean cifrada;
	
	private int tipo;

	public Variavel(String nome, boolean cifrada) {
		super();
		this.nome = nome;
		this.cifrada = cifrada;
	}
	
	public Variavel(String nome, boolean cifrada, int tipo) {
		super();
		this.nome = nome;
		this.cifrada = cifrada;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public boolean isCifrada() {
		return cifrada;
	}

	public int getTipo() {
		return tipo;
	}
	
}
