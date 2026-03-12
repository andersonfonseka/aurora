package ai.aurora.core;

public class ItemMenu {
	
	private String id;
	
	private String titulo;
	
	private Bloco bloco;
	
	private String blocoId;
	
	@Deprecated
	public ItemMenu(String id, String titulo, Bloco bloco) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.bloco = bloco;
	}
	
	public ItemMenu(String id, String titulo, String blocoId) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.blocoId = blocoId;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Bloco getBloco() {
		return bloco;
	}

	public String getBlocoId() {
		return blocoId;
	}
	
}
