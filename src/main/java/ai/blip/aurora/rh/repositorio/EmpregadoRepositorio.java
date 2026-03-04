package ai.blip.aurora.rh.repositorio;

import java.util.HashMap;
import java.util.Map;

import ai.blip.aurora.rh.entidade.Empregado;

public class EmpregadoRepositorio {

	private Map<String, Empregado> dados = new HashMap<>();
	
	public EmpregadoRepositorio() {
		this.dados.put("832.899.924-20", new Empregado("832.899.924-20", "Anderson Fonseca", 5000, "17/02/2025"));
		this.dados.put("796.297.844-91", new Empregado("796.297.844-91", "Sandra Fonseca", 6500, "01/01/2026"));
	} 

	public Empregado obter(String cpf) {
		return this.dados.get(cpf);
	}
	
}
