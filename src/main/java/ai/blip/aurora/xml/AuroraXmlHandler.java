package ai.blip.aurora.xml;

import java.lang.reflect.InvocationTargetException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ai.blip.aurora.core.Bloco;
import ai.blip.aurora.core.BlocoAcao;
import ai.blip.aurora.core.BlocoEncerramento;
import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.BlocoMenu;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.core.ItemMenu;
import ai.blip.aurora.core.Variavel;

public class AuroraXmlHandler extends DefaultHandler {

	private Bot bot;

	private boolean isBlocoOpen = false;
	
	private boolean isBlocoMenuOpen = false;
	
	private boolean isBlocoAcaoOpen = false;

	private Bloco bloco;
	
	private BlocoMenu blocoMenu;
	
	private BlocoAcao blocoAcao;
	
	private BlocoEncerramento blocoEncerramento;

	public Bot getBot() {
		return bot;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		if (qName.equalsIgnoreCase("bot")) {
			bot = new Bot();
			bot.setId(attributes.getValue("id"));
			bot.setBlocoInicial(attributes.getValue("blocoInicial"));
		}

		if (qName.equalsIgnoreCase("bloco")) {

			this.bloco = new Bloco(this.bot);
			bloco.setId(attributes.getValue("id"));
			bloco.setPergunta(attributes.getValue("pergunta"));
			bloco.setProximoBloco(attributes.getValue("proximoBloco"));
			isBlocoOpen = true;
		}

		if (qName.equalsIgnoreCase("entrada") && isBlocoOpen) {
			this.bloco.setVariavelEntrada(new Variavel(attributes.getValue("id"), false));
		}

		if (qName.equalsIgnoreCase("saida") && isBlocoOpen) {
			Variavel var = new Variavel(attributes.getValue("id"), false);
			this.bloco.adicionaVariavelSaida(var);
		}
		
		if (qName.equalsIgnoreCase("saida") && isBlocoMenuOpen) {
			Variavel var = new Variavel(attributes.getValue("id"), false);
			this.blocoMenu.adicionaVariavelSaida(var);
		}

		if (qName.equalsIgnoreCase("blocoMenu")) {
			this.blocoMenu = new BlocoMenu(this.bot);
			blocoMenu.setId(attributes.getValue("id"));
			blocoMenu.setPergunta(attributes.getValue("pergunta"));
			isBlocoMenuOpen = true;
		}
		
		if (qName.equalsIgnoreCase("item") && isBlocoMenuOpen) {
			ItemMenu itemMenu = new ItemMenu(attributes.getValue("id"), 
											 attributes.getValue("titulo"), 
											 attributes.getValue("blocoId"));
			
			this.blocoMenu.adicionarItemMenu(itemMenu);
		}
		
		if (qName.equalsIgnoreCase("blocoAcao")) {

			this.blocoAcao = new BlocoAcao(this.bot);
			blocoAcao.setId(attributes.getValue("id"));
			blocoAcao.setClasse(attributes.getValue("classe"));
			
			isBlocoAcaoOpen = true;
			
		}
		
		if (qName.equalsIgnoreCase("blocoRef") && isBlocoAcaoOpen) {
			this.blocoAcao.adicionarBlocoId(attributes.getValue("id"));
		}
		
		if (qName.equalsIgnoreCase("blocoEncerramento")) {
			
			String classe = attributes.getValue("classe");
			
			try {

				this.blocoEncerramento = (BlocoEncerramento) Class.forName(classe).getDeclaredConstructor(Bot.class).newInstance(this.bot);
				blocoEncerramento.setId(attributes.getValue("id"));
				blocoEncerramento.setPergunta(attributes.getValue("pergunta"));
				blocoEncerramento.setProximoBloco(attributes.getValue("proximoBloco"));

			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("bloco")) {
			isBlocoOpen = false;
		}
		
		if (qName.equalsIgnoreCase("blocoMenu")) {
			isBlocoMenuOpen = false;
			this.blocoMenu.build();
		}
		
		if (qName.equalsIgnoreCase("blocoAcao")) {
			
			String nomeClasse = this.blocoAcao.getClasse();
			
			try {
				
				Class classeExecutora = Class.forName(nomeClasse);
				BlocoExecutor objetoExecutor = (BlocoExecutor) classeExecutora.getDeclaredConstructor(Bot.class, java.util.List.class).newInstance(this.bot, this.blocoAcao.getBlocosId());
				this.blocoAcao.setBlocoExecutor(objetoExecutor);
				objetoExecutor.setBlocoAcionador(blocoAcao);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			isBlocoAcaoOpen = false;
		}
	}
	
	

}
