package ai.blip.aurora.delivery.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ai.blip.aurora.delivery.entidade.Categoria;

public class CategoriaRepositorio {
	
	private Map<String, Categoria> mapCategorias = new HashMap<>();
	
	public CategoriaRepositorio() {
		
		mapCategorias.putIfAbsent("1", new Categoria("1", "Pizzas Salgadas", 1));
		mapCategorias.putIfAbsent("2", new Categoria("2", "Pizzas Doces", 1));
		mapCategorias.putIfAbsent("3", new Categoria("3", "Bebidas", 1));
		
		mapCategorias.putIfAbsent("4", new Categoria("4", "Medicamentos Isentos", 2));
		mapCategorias.putIfAbsent("5", new Categoria("5", "Higiene e Cuidados Pessoais", 2));
		mapCategorias.putIfAbsent("6", new Categoria("6", "Mamãe e Bebê", 2));
	}
	
	public List<Categoria> obterLista(int lojistaId) {
		return this.mapCategorias.values().stream().filter(x -> x.lojistaId() == lojistaId).collect(Collectors.toList());
	}
	
	public Categoria obter(String id) {
		return this.mapCategorias.get(id);
	}

}
