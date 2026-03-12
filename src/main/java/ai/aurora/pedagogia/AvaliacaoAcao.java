package ai.aurora.pedagogia;

import ai.aurora.core.BlocoExecutor;

public class AvaliacaoAcao extends BlocoExecutor {

    @Override
    public void run() {
    	
    	String assunto = getBot().obterValor("assunto_estudo");
        String respostaAlunos = getBot().obterValor("resposta_aluno");
        String gabarito = getBot().obterValor("gabarito");
        int nivelAtual = Integer.parseInt(getBot().obterValor("nivel_atual"));

        // A IA compara semanticamente a resposta (não apenas texto exato)
        AvaliacaoResultado resultado = ServicoIA.avaliar(assunto, respostaAlunos, gabarito);

        if ((boolean) resultado.isCorreto()) {
            // Sobe o nível e vai para feedback positivo (blocoRef 0)
            getBot().adicionar(gabarito, resultado);
            getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
        } else {
            // Mantém ou desce o nível e envia explicação (blocoRef 1)
            getBot().adicionar("explicacao_erro", resultado.getFeedback());
            getBlocoAcionador().setProximoBloco(getBlocosId().get(1));
        }
        
        // Persistência do progresso semanal (Lead/Progresso)
        ProgressoRepositorio.salvar(getBot().getChatId(), nivelAtual, resultado.isCorreto());
    }
}