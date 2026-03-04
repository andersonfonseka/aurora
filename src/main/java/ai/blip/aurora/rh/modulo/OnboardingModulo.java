package ai.blip.aurora.rh.modulo;

import java.util.Arrays;

import ai.blip.aurora.core.Bloco;
import ai.blip.aurora.core.BlocoAcao;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.core.Modulo;
import ai.blip.aurora.core.Variavel;
import ai.blip.aurora.rh.acao.EmpregadoAcao;

public class OnboardingModulo extends Modulo {
	
	private	Bloco saudacao;
	
	private Bloco dadosEntrada;
	
	private Bloco dadosConfirmacao;
	
	private BlocoAcao empregadoAcao;
	
	private Bloco blocoRetorno;
	
    public OnboardingModulo(Bot bot) {
    	super(bot);
    	
    	this.saudacao = new Bloco(bot);
    	this.dadosEntrada = new Bloco(bot);
    	this.dadosConfirmacao = new Bloco(bot);
    	this.empregadoAcao = new BlocoAcao(bot);
        
    	saudacao.setPergunta(getProp().obter("onboarding.saudacao"));
		saudacao.setVariavelEntrada(new Variavel("nome", false));
		saudacao.setProximoBloco(dadosEntrada.getId());

		dadosEntrada.setPergunta(getProp().obter("onboarding.dados_cpf"));
		dadosEntrada.setVariavelSaida(Arrays.asList(new Variavel[] { new Variavel("nome", false)}));
		dadosEntrada.setVariavelEntrada(new Variavel("cpf", true));
		dadosEntrada.setProximoBloco(dadosConfirmacao.getId());

		dadosConfirmacao.setPergunta(getProp().obter("onboarding.confirma_dados_cpf"));
		dadosConfirmacao.setVariavelSaida(Arrays.asList(new Variavel[] { new Variavel("nome", false), new Variavel("cpf", true)}));
		dadosConfirmacao.setVariavelEntrada(new Variavel("dados_confirmados", false));
		dadosConfirmacao.setProximoBloco(empregadoAcao.getId());
		
        setBlocoInicial(saudacao);
    }
    
    public void setBlocoRetorno(Bloco blocoRetorno) {
		this.blocoRetorno = blocoRetorno;
		
		empregadoAcao.adicionarBlocoId(dadosEntrada.getId());
		empregadoAcao.adicionarBlocoId(dadosConfirmacao.getId());
		empregadoAcao.adicionarBlocoId(this.blocoRetorno.getId());
		
		empregadoAcao.execute(new EmpregadoAcao(getBot(), empregadoAcao.getBlocosId()));
		
	}

}
