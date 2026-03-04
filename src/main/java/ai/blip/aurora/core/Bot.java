package ai.blip.aurora.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Bot {
	
	private String id = UUID.randomUUID().toString();
	
	private MensagemUtil prop = MensagemUtil.obterInstancia();
	
	private Map<String, Object> variaveis = new HashMap<>();
	
	private Map<String, Bloco> blocos = new HashMap<>();
	
	private List<Bloco> blocosNavegados = new ArrayList<>();
	
	private String blocoInicialId;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Bloco getBlocoInicial() {
		return this.blocos.get(this.blocoInicialId);
	}
	
	public String getBlocoInicialId() {
		return blocoInicialId;
	}

	public void setBlocoInicial(String blocoInicial) {
		this.blocoInicialId = blocoInicial;
	}

	public void adicionar(String chave, Object valor) {
		this.variaveis.put(chave, valor);
	}
	
	public Object obterValor(String chave) {
		return this.variaveis.get(chave);
	}
	
	public MensagemUtil getProp() {
		return prop;
	}
	
	public void adicionar(Bloco bloco) {
		this.blocos.putIfAbsent(bloco.getId(), bloco);
	}
	
	public Bloco obterBloco(String chave) {
		return this.blocos.get(chave);
	}
	
	public Bloco getBlocosNavegadosPorTipo(Class clazz) {
		
		Bloco bloco = null;
		
		for (Bloco blocoIn : blocosNavegados.reversed()) {
			if (blocoIn.getClass().getTypeName().equals(clazz.getTypeName())) {
				bloco = blocoIn;
				break;
			}
		}
		
		return bloco;
	}
	
	public String processar(String mensagem) throws CloneNotSupportedException {

		String resposta = "";
		
		Bloco bloco = getBlocoInicial();
		
		if (bloco.getPergunta().trim().length() > 0) {
			resposta = bloco.getPergunta();	
		}
		
		if (bloco instanceof BlocoAcao) {
			((BlocoAcao) bloco).execute();
		} else if (bloco.getVariavelEntrada() != null) {
			bloco.setResposta(mensagem);
		}  
	
		this.blocosNavegados.add((Bloco) bloco.clone());
		bloco = bloco.getProximoBloco();
			
		return resposta;
	}

	public void run() throws CloneNotSupportedException {
		
		Scanner scanner = new Scanner(System.in);
		
		Bloco bloco = getBlocoInicial();
		
		while(bloco != null) {
			
			if (bloco.getPergunta().trim().length() > 0) {
				System.out.println(bloco.getPergunta());	
			}
			
			if (bloco instanceof BlocoAcao) {
				((BlocoAcao) bloco).execute();
			} else if (bloco.getVariavelEntrada() != null) {
				String valor = scanner.nextLine();
				bloco.setResposta(valor);
			}  
		
			this.blocosNavegados.add((Bloco) bloco.clone());
			bloco = bloco.getProximoBloco();

		}
		
		scanner.close();
	}
	
}
