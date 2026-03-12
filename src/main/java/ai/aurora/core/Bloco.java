package ai.aurora.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bloco implements Cloneable {

	private String id = UUID.randomUUID().toString();
	
	private Bot bot;
	
	private String proximoBlocoId;
	
	private String pergunta = "";
	
	private String perguntaTemplate = "";
	
	private String resposta = "";
	
	private Variavel variavelEntrada;
	
	private List<Variavel> variavelSaida = new ArrayList<Variavel>();
	
	public Bloco(Bot bot) {
		this.bot = bot;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
		bot.adicionar(this);
	}
	
	public Bot getBot() {
		return bot;
	}

	public String getPergunta() {
		
		String result =  this.perguntaTemplate;
		
		if (this.variavelSaida != null) {
			for (int i = 0; i < variavelSaida.size(); i++) {
				
				if (!this.variavelSaida.get(i).isCifrada() && (bot.obterValor(this.variavelSaida.get(i).getNome()) != null)) {
					result = result.replace("{" + String.valueOf(this.variavelSaida.get(i).getNome()) + "}", String.valueOf(bot.obterValor(this.variavelSaida.get(i).getNome())));	
				} else if (this.variavelSaida.get(i).isCifrada() && (bot.obterValor(this.variavelSaida.get(i).getNome()) != null)) {
					result = result.replace("{" + String.valueOf(this.variavelSaida.get(i).getNome()) + "}", String.valueOf(bot.obterValor(this.variavelSaida.get(i).getNome())).replaceAll("\\d", "X"));
				}
			}
		}
		
		return result;
	}

	public void setPergunta(String pergunta) {
		
		if (this instanceof BlocoMenu) {
			this.pergunta = pergunta + "{"+ this.getId() + "_itensMenu" +"}";
		} else {
			this.pergunta = pergunta;	
		}

		this.perguntaTemplate = this.pergunta;	
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
		
		if (null != this.variavelEntrada) {
			this.bot.adicionar(this.variavelEntrada.getNome(), resposta);	
		}
	}

	public Variavel getVariavelEntrada() {
		return variavelEntrada;
	}

	public void setVariavelEntrada(Variavel variavelEntrada) {
		this.variavelEntrada = variavelEntrada;
	}

	public List<Variavel> getVariavelSaida() {
		return variavelSaida;
	}
	
	public void setVariavelSaida(List<Variavel> variavelSaida) {
		this.variavelSaida = variavelSaida;
	}

	public void adicionaVariavelSaida(Variavel varivel) {
		this.variavelSaida.add(varivel);
	}
		
	public Bloco getProximoBloco() {
		return this.bot.obterBloco(proximoBlocoId);
	}
	
	public void setProximoBloco(String proximoBloco) {
		this.proximoBlocoId = proximoBloco;
	}

	public String getProximoBlocoId() {
		return proximoBlocoId;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
		
}
