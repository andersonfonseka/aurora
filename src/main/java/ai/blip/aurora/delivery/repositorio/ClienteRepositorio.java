package ai.blip.aurora.delivery.repositorio;

import java.util.HashMap;
import java.util.Map;

import ai.blip.aurora.delivery.entidade.Cliente;

public class ClienteRepositorio {

	private Map<String, Cliente> dados = new HashMap<>();
	
	public ClienteRepositorio() {
		this.dados.put("81992240951", new Cliente("81992240951", "Anderson Fonseca", "Av Tucunare, 1192"));
	} 

	public Cliente obter(String id) {
		return this.dados.get(id);
	}
	
}
