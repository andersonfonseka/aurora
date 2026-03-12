package ai.aurora.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import ai.aurora.telegram.ContextoManager;
import ai.aurora.telegram.ContextoUsuario;

public class Bot {
	
	private String id = UUID.randomUUID().toString();
	
	private long chatId;
	
	private MensagemUtil prop = MensagemUtil.obterInstancia();
	
	private Map<String, Object> variaveis = new HashMap<>();
	
	private Map<String, Bloco> blocos = new HashMap<>();
	
	private List<Bloco> blocosNavegados = new ArrayList<>();
	
	private String blocoInicialId;
	
	// Necessário para o controle do inicio da conversa
	private int countBlocoInicial = 0;
	
	//Controle de Blocos para o Telegrama
	private Bloco bloco;

	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
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
	
	public String obterValor(String chave) {
		return String.valueOf(this.variaveis.get(chave));
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
	
	public int getCountBlocoInicial() {
		return countBlocoInicial;
	}

	public void setCountBlocoInicial(int countBlocoInicial) {
		this.countBlocoInicial = countBlocoInicial;
	}

	public Bloco getBlocosNavegadosPorTipo(Class clazz) {
		
		Bloco bloco = null;
		
		Collections.reverse(blocosNavegados);
		
		for (Bloco blocoIn : blocosNavegados) {
			if (blocoIn.getClass().getTypeName().equals(clazz.getTypeName())) {
				bloco = blocoIn;
				break;
			}
		}
		
		return bloco;
	}
	
		
	public String processar(ContextoUsuario ctu, String mensagem) throws CloneNotSupportedException {

		String resposta = "";
		
		if (countBlocoInicial < 2) {
			this.bloco = getBlocoInicial();
			
			if (countBlocoInicial == 0 && this.bloco.getPergunta().trim().length() > 0) {
			
				resposta = this.bloco.getPergunta();	
			
			} else if (countBlocoInicial == 1) {
				this.bloco.setResposta(mensagem);
				
				this.blocosNavegados.add((Bloco) this.bloco.clone());
				this.bloco = this.bloco.getProximoBloco();
				
				resposta = this.bloco.getPergunta();
			}
			
			countBlocoInicial++;
					
		} else {
			
			 if (this.bloco.getVariavelEntrada() != null) {
				this.bloco.setResposta(mensagem);
				
				this.blocosNavegados.add((Bloco) this.bloco.clone());
				this.bloco = this.bloco.getProximoBloco();
				
				trataBlocoAcao(this.bloco);
				
				resposta = this.bloco.getPergunta();
			}
		}
				
		ctu.atualizarInteracao();
		return resposta;
	}

	private void trataBlocoAcao(Bloco bloco) throws CloneNotSupportedException {
		
		if (this.bloco instanceof BlocoAcao) {
			((BlocoAcao) this.bloco).execute();
			
			this.blocosNavegados.add((Bloco) this.bloco.clone());
			this.bloco = this.bloco.getProximoBloco();
			
			if (this.bloco instanceof BlocoAcao) {
				trataBlocoAcao(this.bloco);
			}
		}
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
			} else if(bloco instanceof BlocoEncerramento) {
				BlocoEncerramento blocoEncerramento = (BlocoEncerramento) bloco; 
				blocoEncerramento.build();
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
