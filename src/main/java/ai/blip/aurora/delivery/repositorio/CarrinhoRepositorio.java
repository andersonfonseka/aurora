package ai.blip.aurora.delivery.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.blip.aurora.delivery.entidade.Produto;

public class CarrinhoRepositorio {
	
	private Map<String, List<Produto>> produtos = new HashMap<>();
	
	private static CarrinhoRepositorio INSTANCE;
	
	private CarrinhoRepositorio() {}
	
	public static CarrinhoRepositorio obterInstancia() {
		if (INSTANCE == null) {
			INSTANCE = new CarrinhoRepositorio();
		}
		return INSTANCE;
	}
	
	public void adicionar(String id, Produto produto){

		if (this.produtos.containsKey(id)) {
			List<Produto> prds = this.produtos.get(id);
			prds.add(produto);
		} else {
			List<Produto> prds = new ArrayList<>();
			prds.add(produto);
			this.produtos.put(id, prds);
		}
	}
	
	public String obterDetalhamentoCompra(String id) {

		StringBuilder sb = new StringBuilder();

		List<Produto> prds = this.produtos.get(id);

		sb.append("\n");	
		
		for (Produto produto : prds) {
			sb.append("- " + produto.descricao() + " " + produto.valor() + "\n");
		}
		
		sb.append("\n");

		return sb.toString();
	}
	
	public double getProdutosValorTotal(String id) {
		
		List<Produto> prds =  this.produtos.get(id);
		
		double valor = prds
						.stream()
						.mapToDouble(x -> x.valor())
						.sum();
		
		return valor;
	}

}
