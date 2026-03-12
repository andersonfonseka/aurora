package ai.aurora.pedagogia;

import ai.aurora.core.BlocoExecutor;

public class GeradorAulaAcao extends BlocoExecutor {

    @Override
    public void run() {
        // 1. Recupera os dados do contexto do aluno
        String assunto = getBot().obterValor("assunto_estudo");
        String nivelStr = getBot().obterValor("nivel_atual");
        
        // Inicializa o nível se for a primeira aula
        int nivel = (nivelStr == null) ? 1 : Integer.parseInt(nivelStr);

        // 2. Chama o serviço de pedagogia (IA) para gerar o conteúdo da aula
        // Passamos o nível para que a IA use linguagem mais simples ou complexa
        String conteudoAula = ServicoIA.gerarMicroAula(assunto, nivel);

        // 3. Salva o conteúdo gerado na variável que o XML espera: {conteudo_aula}
        getBot().adicionar("conteudo_aula", conteudoAula);

        // 4. Define o próximo bloco como o primeiro definido no <blocos> do XML
        // No seu XML, este é o id="aula.exibicao"
        if (getBlocosId() != null && !getBlocosId().isEmpty()) {
            getBlocoAcionador().setProximoBloco(getBlocosId().get(0));
        }
    }
}