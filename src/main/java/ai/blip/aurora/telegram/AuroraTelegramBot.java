package ai.blip.aurora.telegram;

import java.util.HashMap;
import java.util.Map;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ai.blip.aurora.core.Bot;
import ai.blip.aurora.core.MensagemUtil;
import ai.blip.aurora.xml.AuroraXmlParser;

public class AuroraTelegramBot extends TelegramLongPollingBot {

    private Map<Long, Bot> sessoes = new HashMap<>();
    private AuroraXmlParser parser = new AuroraXmlParser();

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String textoUsuario = update.getMessage().getText();

            Bot botDoUsuario = sessoes.computeIfAbsent(chatId, id -> {
                
            	try {
                    return parser.carregar(getClass().getResourceAsStream("/bot/marketplace.xml"), chatId);
                } catch (Exception e) {
                    return null;
                }
            });

            String resposta;

            try {
				resposta = botDoUsuario.processar(textoUsuario);
			    enviarMensagem(chatId, resposta);
				
			} catch (CloneNotSupportedException e) {
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
    public String getBotUsername() { return "PizzaAurora_bot"; }

    @Override
    public String getBotToken() { 
    	return MensagemUtil.obterInstancia().obter("key"); 
    }
}