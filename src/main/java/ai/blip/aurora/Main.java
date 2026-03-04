package ai.blip.aurora;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ai.blip.aurora.telegram.AuroraTelegramBot;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Inicializa a API de Sessão do Telegram
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            // 2. Registra o seu AuroraTelegramBot
            // Ele vai começar a escutar as mensagens imediatamente
            botsApi.registerBot(new AuroraTelegramBot());

            System.out.println("🚀 Aurora Core está online no Telegram!");
            System.out.println("Aguardando pedidos de pizza...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}