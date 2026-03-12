package ai.aurora.telegram;

import java.util.HashMap;
import java.util.Map;

public class ContextoUsuario {

	private long chatId;
	private long ultimaInteracao;
	private Map<String, Object> variaveis = new HashMap<>();

	public ContextoUsuario(long chatId) {
		this.chatId = chatId;
		this.atualizarInteracao();
	}

	public void atualizarInteracao() {
		this.ultimaInteracao = System.currentTimeMillis();
	}

	public long getUltimaInteracao() {
		return ultimaInteracao;
	}

}
