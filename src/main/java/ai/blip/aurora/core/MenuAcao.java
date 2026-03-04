package ai.blip.aurora.core;

import java.util.List;
import java.util.Map;

public class MenuAcao extends BlocoExecutor {

	private String menuPrincipal;
	
	private Map<String, ItemMenu> opcoesMenu;
	
	public MenuAcao(Bot bot, Map<String, ItemMenu> opcoesMenu, List<String> blocosId) {
		super(bot, blocosId);
		this.opcoesMenu = opcoesMenu;
		this.menuPrincipal = blocosId.get(0);
	}

	public void run() {
		
		BlocoMenu menuPrincipal = (BlocoMenu) getBot().obterBloco(this.menuPrincipal);
		
		String texto = menuPrincipal.getPergunta();
		
		if (this.opcoesMenu.containsKey(getBot().obterValor(menuPrincipal.getVariavelOpcao()))) {
			getBlocoAcionador().setProximoBloco(opcoesMenu.get(getBot().obterValor(menuPrincipal.getVariavelOpcao())).getBlocoId());
		} else {
			getBlocoAcionador().setProximoBloco(menuPrincipal.getId());
		}
		
		getBot().adicionar(menuPrincipal.getVariavelOpcao(), "");
	}
}
