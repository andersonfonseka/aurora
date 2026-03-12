package ai.aurora.rh.repositorio;

import java.util.HashMap;
import java.util.Map;

import ai.aurora.rh.entidade.Ferias;

public class FeriasRepositorio {

	private Map<String, Ferias> map = new HashMap<>();
	
	public void agendar(Ferias ferias) {
		this.map.putIfAbsent(ferias.getCpf(), ferias);
	}
	
}
