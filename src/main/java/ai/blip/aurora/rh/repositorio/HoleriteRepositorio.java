package ai.blip.aurora.rh.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.blip.aurora.rh.entidade.Holerite;

public class HoleriteRepositorio {

	private Map<String, List<Holerite>> dados = new HashMap<>();
	
	public HoleriteRepositorio() {
		
		List<Holerite> list = new ArrayList<>();
		list.add(new Holerite("Dezembro/25", 4250));
		list.add(new Holerite("Janeiro/26", 4200));
		list.add(new Holerite("Fevereiro/26", 4330));
		
		this.dados.put("832.899.924-20", list);
		this.dados.put("796.297.844-91", list);
		
	} 

	public List<Holerite> obter(String cpf) {
		return this.dados.get(cpf);
	}
	
}
