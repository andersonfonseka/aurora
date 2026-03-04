package ai.blip.aurora.delivery.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ai.blip.aurora.delivery.entidade.Produto;

public class ProdutoRepositorio {
	
	private Map<String, Produto> mapProdutos = new HashMap<>();
	
	public ProdutoRepositorio() {
		mapProdutos.putIfAbsent("001", new Produto("001", "Calabresa", 45, 1));
		mapProdutos.putIfAbsent("002", new Produto("002", "Marguerita", 42, 1));
		mapProdutos.putIfAbsent("003", new Produto("003", "Portuguesa", 50, 1));
		
		mapProdutos.putIfAbsent("004", new Produto("004", "Chocolate com Morango", 35, 2));
		mapProdutos.putIfAbsent("005", new Produto("005", "Romeu e Julieta", 32, 2));
		mapProdutos.putIfAbsent("006", new Produto("006", "Banana com Canela", 30, 2));
		
		mapProdutos.putIfAbsent("007", new Produto("007", "Coca-Cola 2L", 12, 3));
		mapProdutos.putIfAbsent("008", new Produto("008", "Guaraná 2L", 10, 3));
		mapProdutos.putIfAbsent("009", new Produto("009", "Suco de Laranja 1L", 15, 3));
		mapProdutos.putIfAbsent("010", new Produto("010", "Água Mineral 500ml", 5, 3));
		
	}
	
	public List<Produto> obterLista(int categoria) {
		return this.mapProdutos.values().stream().filter(x -> x.categoria() == categoria).collect(Collectors.toList());
	}
	
	public Produto obter(String id) {
		return this.mapProdutos.get(id);
	}

}
