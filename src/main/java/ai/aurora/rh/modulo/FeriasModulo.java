package ai.aurora.rh.modulo;

import java.util.Arrays;

import ai.aurora.core.Bloco;
import ai.aurora.core.BlocoAcao;
import ai.aurora.core.Bot;
import ai.aurora.core.Modulo;
import ai.aurora.core.Variavel;
import ai.aurora.core.VariavelTipo;
import ai.aurora.rh.acao.FeriasAcao;

public class FeriasModulo extends Modulo {
	
	private Bloco perguntaData;
	
	private BlocoAcao acaoValidar;
	
	private Bloco sucesso;
	
	private Bloco erroTempo;
	
	private Bloco blocoRetorno;

    public FeriasModulo(Bot bot) {
        super(bot);
    	
    	this.perguntaData = new Bloco(bot);
    	this.acaoValidar = new BlocoAcao(bot);
    	this.sucesso = new Bloco(bot);
    	this.erroTempo = new Bloco(bot);

        perguntaData.setPergunta(getProp().obter("ferias.saudacao"));
        perguntaData.setVariavelSaida(Arrays.asList(new Variavel[] {new Variavel("nome", false)}));
        perguntaData.setVariavelEntrada(new Variavel("dataFerias", false, VariavelTipo.DATA));
        perguntaData.setProximoBloco(acaoValidar.getId());

        acaoValidar.adicionarBlocoId(sucesso.getId());
        acaoValidar.adicionarBlocoId(erroTempo.getId());
        
        acaoValidar.execute(new FeriasAcao(acaoValidar));

        sucesso.setPergunta(getProp().obter("ferias.sucesso"));
        sucesso.setVariavelSaida(Arrays.asList(new Variavel[] {new Variavel("nome", false)}));

        erroTempo.setPergunta(getProp().obter("ferias.erro"));
        erroTempo.setVariavelSaida(Arrays.asList(new Variavel[] {
            new Variavel("nome", false), 
            new Variavel("dataAdmissao", false), 
            new Variavel("tempoDecorrido", false)
        }));

        setBlocoInicial(perguntaData);
    }
    
	public void setBlocoRetorno(Bloco blocoRetorno) {
		this.blocoRetorno = blocoRetorno;
        this.sucesso.setProximoBloco(this.blocoRetorno.getId());
		this.erroTempo.setProximoBloco(this.blocoRetorno.getId()); 
	}

}
