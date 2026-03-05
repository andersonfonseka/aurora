package ai.blip.aurora.delivery.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ai.blip.aurora.delivery.entidade.Produto;

public class ProdutoRepositorio {
	
	private Map<String, Produto> mapProdutos = new HashMap<>();
	
	public ProdutoRepositorio() {
		
		mapProdutos.putIfAbsent("1", new Produto("1", "Calabresa", 45, 1));
		mapProdutos.putIfAbsent("2", new Produto("2", "Marguerita", 42, 1));
		mapProdutos.putIfAbsent("3", new Produto("3", "Portuguesa", 50, 1));
		
		mapProdutos.putIfAbsent("4", new Produto("4", "Chocolate com Morango", 35, 2));
		mapProdutos.putIfAbsent("5", new Produto("5", "Romeu e Julieta", 32, 2));
		mapProdutos.putIfAbsent("6", new Produto("6", "Banana com Canela", 30, 2));
		
		mapProdutos.putIfAbsent("7", new Produto("7", "Coca-Cola 2L", 12, 3));
		mapProdutos.putIfAbsent("8", new Produto("8", "Guaraná 2L", 10, 3));
		mapProdutos.putIfAbsent("9", new Produto("9", "Suco de Laranja 1L", 15, 3));
		mapProdutos.putIfAbsent("10", new Produto("10", "Água Mineral 500ml", 5, 3));
		
		mapProdutos.putIfAbsent("11", new Produto("11", "Paracetamol 750mg", 15.5, 4));
		mapProdutos.putIfAbsent("12", new Produto("12", "Dipirona Monoidratada", 8.5, 4));
		mapProdutos.putIfAbsent("13", new Produto("13", "Antiácido Estomazil", 4.2, 4));
		
		mapProdutos.putIfAbsent("14", new Produto("14", "Kit Shampoo & Condicionador", 35, 5));
		mapProdutos.putIfAbsent("15", new Produto("15", "Desodorante Rexona Clinical", 22.9, 5));
		mapProdutos.putIfAbsent("16", new Produto("16", "Creme Dental Colgate Total 12", 12, 5));
		
		mapProdutos.putIfAbsent("17", new Produto("17", "Fralda Pampers Ajuste Total G", 89.9, 6));
		mapProdutos.putIfAbsent("18", new Produto("18", "Lenços Umedecidos Huggies", 18, 6));
		mapProdutos.putIfAbsent("19", new Produto("19", "Pomada Bepantol Baby", 29.9, 6));
		
	}
	
	public List<Produto> obterLista(int categoria) {
		return this.mapProdutos.values().stream().filter(x -> x.categoria() == categoria).collect(Collectors.toList());
	}
	
	public Produto obter(String id) {
		return this.mapProdutos.get(id);
	}

}
