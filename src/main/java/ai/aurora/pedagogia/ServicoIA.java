package ai.aurora.pedagogia;

public class ServicoIA {

    public static String gerarMicroAula(String assunto, int nivel) {
        
    	if (assunto.equalsIgnoreCase("Matematica") && nivel == 1) {
            return "### Aula 1: O Poder da Adição ➕\n\n" +
                   "Somar é o ato de juntar quantidades. Por exemplo, se você tem **2 maçãs** " +
                   "e ganha mais **1**, agora você tem **3**!\n\n" +
                   "O símbolo que usamos é o **+**.";
        }
        
    	if (assunto.equalsIgnoreCase("Geografia") && nivel == 1) {
    	    return "### Aula 1: Localização e Pontos Cardeais 🧭\n\n" +
    	           "Para não ficarmos perdidos, usamos os **Pontos Cardeais**. Eles são as direções principais que usamos no mundo todo:\n\n" +
    	           "1. **Norte (N)**: Fica à nossa frente.\n" +
    	           "2. **Sul (S)**: Fica atrás de nós.\n" +
    	           "3. **Leste (L)**: Onde o Sol nasce.\n" +
    	           "4. **Oeste (O)**: Onde o Sol se põe.";
    	}
        
        return "Conteúdo padrão para " + assunto + " nível " + nivel;
    }

    public static Exercicio gerarQuestao(String assunto, int nivel) {

    	Exercicio exercicio = new Exercicio();
    	
    	if (assunto.equalsIgnoreCase("Matematica") && nivel == 1) {
        	
        	exercicio.setEnunciado("Vamos praticar! Se João tem 3 lápis e Maria dá a ele mais 2 lápis, " +
                   "com quantos lápis João ficou no total?");
        	
        	exercicio.setRespostaCorreta("5");
        } else if (assunto.equalsIgnoreCase("Geografia") && nivel == 1) {

        	exercicio.setEnunciado("Imagine que você está vendo o Sol nascer logo cedo na sua frente. " +
                    "Em qual ponto cardeal você está olhando?");
         	
         	exercicio.setRespostaCorreta("Leste");
         }
    	
    	else {
        	exercicio.setEnunciado("Quanto é 1 + 1?");
        	exercicio.setRespostaCorreta("2");
        }
    	
        return exercicio;
    }

    public static AvaliacaoResultado avaliar(String assunto, String respostaAluno, String gabaritoEsperado) {
    	
    	boolean correto = false;
        String feedback = "";
    	
    	if (assunto.equalsIgnoreCase("Matematica")) {
            
	    	correto = respostaAluno.contains("5");
	        feedback = correto ? 
	            "Excelente! Você somou perfeitamente." : 
	            "Ops, não é bem isso. Tente contar nos dedos: 3... 4... 5!";
    	}
        
        if (assunto.equalsIgnoreCase("Geografia")) {

        	correto = respostaAluno.toLowerCase().contains("leste");
            feedback = correto ? 
                "Perfeito! O Sol sempre 'nasce' no Leste." : 
                "Quase lá! Lembre-se: o Sol nasce no Leste e se põe no Oeste. Tente novamente!";
                
        }
        
        return new AvaliacaoResultado(correto, feedback);
    }
}
