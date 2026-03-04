package ai.blip.aurora.telegram;

import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ai.blip.aurora.core.Bot;
import ai.blip.aurora.xml.AuroraXmlParser;

public class AuroraTelegramBot extends TelegramLongPollingBot {

    // Mapa de sessões: ChatId -> Instância do Bot do Usuário
    private Map<Long, Bot> sessoes = new HashMap<>();
    private AuroraXmlParser parser = new AuroraXmlParser();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String textoUsuario = update.getMessage().getText();

            // 1. Recupera ou cria o bot para este usuário específico
            Bot botDoUsuario = sessoes.computeIfAbsent(chatId, id -> {
                try {
                    // Carrega o XML da Pizzaria ou RH
                    return parser.carregar(getClass().getResourceAsStream("/bot/pizza_delivery.xml"));
                } catch (Exception e) {
                    return null;
                }
            });

            // 2. Processa a mensagem na sua Engine
            String resposta;
			try {
				resposta = botDoUsuario.processar(textoUsuario);
				
				// 3. Envia a resposta de volta para o Telegram
	            enviarMensagem(chatId, resposta);
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            
        }
    }

    private void enviarMensagem(long chatId, String texto) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(texto);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() { return "AuroraRH_Bot"; }

    @Override
    public String getBotToken() { return "8390091402:AAHNVA8BnZp4Za2v8LbI5V0h6d7ChVjDcWI"; }
}