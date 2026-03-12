package ai.aurora.pedagogia;

import ai.aurora.core.BlocoExecutor;

public class InicializarTrilhaAcao extends BlocoExecutor {

    @Override
    public void run() {
        String chatId = String.valueOf(getBot().getChatId());
        
        // Simula busca no banco de dados de progresso
        // ProgressoRepositorio seria sua classe de acesso a dados (DAO/JPA)
        int nivelSalvo = ProgressoRepositorio.obterNivelAtual(chatId);
        
        if (nivelSalvo <= 0) {
            nivelSalvo = 1; // Aluno novo começa no nível 1
        }

        // Armazena no contexto do bot para as próximas classes usarem
        getBot().adicionar("nivel_atual", String.valueOf(nivelSalvo));
        
        // Define que o próximo passo é gerar a primeira microaula
        // getBlocosId().get(0) aponta para "acao.gerar.aula" no XML
        getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
    }
}