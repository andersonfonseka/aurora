package ai.blip.aurora.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlocoMenu extends Bloco {
	
	private Map<String, ItemMenu> blocosMenu = new HashMap<>();
	
	private List<ItemMenu> items = new ArrayList<>();
	
	private BlocoAcao menuAcao;

	public BlocoMenu(Bot bot) {
		super(bot);
		this.menuAcao = new BlocoAcao(bot);
		
	}
	
	public String getVariavelOpcao() {
        return this.getId() + "_opcao";
    }

	public BlocoMenu adicionarItemMenu(ItemMenu itemMenu) {
		this.blocosMenu.put(itemMenu.getId(), itemMenu);
		items.add(itemMenu);
		return this;
	}
	
	public void clear() {
		this.blocosMenu.values().clear();
		this.items.clear();
	}

	public void build() {
		
	    this.setVariavelEntrada(new Variavel(getVariavelOpcao(), false));
		this.setProximoBloco(this.menuAcao.getId());
		
		this.menuAcao.adicionarBlocoId(this.getId());
		this.menuAcao.execute(new MenuAcao(getBot(), blocosMenu, this.menuAcao.getBlocosId()));
		getBot().adicionar(this.menuAcao);
		
		StringBuilder sb = new StringBuilder();
		
		for (ItemMenu itm: this.items) {
			sb.append("\n" + itm.getId() + ". " + itm.getTitulo());  
		}
		
		this.adicionaVariavelSaida(new Variavel(this.getId() + "_itensMenu", false, 1));
		
		getBot().adicionar(this.getId() + "_itensMenu", sb.toString());	

	}
}
