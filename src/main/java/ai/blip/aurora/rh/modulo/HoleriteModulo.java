package ai.blip.aurora.rh.modulo;

import java.util.Arrays;

import ai.blip.aurora.core.Bloco;
import ai.blip.aurora.core.BlocoAcao;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.core.Modulo;
import ai.blip.aurora.core.Variavel;
import ai.blip.aurora.rh.acao.HoleriteAcao;

public class HoleriteModulo extends Modulo {
	
	private	BlocoAcao holeriteAcao;
	
	private Bloco holeriteSucesso;

	private Bloco blocoRetorno;

    public HoleriteModulo(Bot bot) {
    	super(bot);
    		
    	this.holeriteAcao = new BlocoAcao(bot);
		this.holeriteSucesso = new Bloco(bot);

		holeriteAcao.adicionarBlocoId(holeriteSucesso.getId());
		
		holeriteAcao.execute(new HoleriteAcao(bot, holeriteAcao.getBlocosId()));

		holeriteSucesso.setPergunta(getProp().obter("holerite.sucesso"));
		holeriteSucesso.setVariavelSaida(Arrays.asList(new Variavel[] { new Variavel("resultado", false), new Variavel("nome", false) }));
    	
        setBlocoInicial(holeriteAcao);
    }

	public void setBlocoRetorno(Bloco blocoRetorno) {
		this.blocoRetorno = blocoRetorno;
		holeriteSucesso.setProximoBloco(this.blocoRetorno.getId());
	}

}
