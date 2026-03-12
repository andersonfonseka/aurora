package ai.aurora.pedagogia;

import ai.aurora.core.BlocoExecutor;

public class GeradorExercicioAcao extends BlocoExecutor {

    @Override
    public void run() {
        String assunto = getBot().obterValor("assunto_estudo");
        // O nível é incrementado ou decrementado pela classe AvaliacaoAcao
        int nivelDificuldade = Integer.parseInt(getBot().obterValor("nivel_atual"));

        // Simulação de chamada para IA gerando exercício personalizado
        Exercicio exercicio = ServicoIA.gerarQuestao(assunto, nivelDificuldade);

        getBot().adicionar("pergunta_exercicio", exercicio.getEnunciado());
        getBot().adicionar("gabarito", exercicio.getRespostaCorreta());

        // Segue para exibir a pergunta (blocoRef 0 no XML)
        getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
    }
}