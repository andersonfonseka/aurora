package ai.aurora.telegram;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ContextoManager {

	private final Map<Long, ContextoUsuario> sessoes = new ConcurrentHashMap<>();
	private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

	private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(30);

	public ContextoManager() {
		scheduler.scheduleAtFixedRate(this::limparSessoesInativas, 5, 5, TimeUnit.MINUTES);
	}

	private void limparSessoesInativas() {
	
		long agora = System.currentTimeMillis();

		sessoes.entrySet().removeIf(entry -> {
			boolean inativo = (agora - entry.getValue().getUltimaInteracao()) > EXPIRATION_TIME;
			if (inativo) {
				System.out.println("🧹 Aurora Cleanup: Removendo sessão inativa do ChatID " + entry.getKey());
			}
			return inativo;
		});
	}

	public ContextoUsuario getContexto(Long chatId) {
		
		ContextoUsuario ctx = sessoes.computeIfAbsent(chatId, id -> new ContextoUsuario(id));
		ctx.atualizarInteracao(); 
		return ctx;
	}
}