package ai.aurora.core;

import java.util.Map;

public class MenuAcao extends BlocoExecutor {

	private Map<String, ItemMenu> opcoesMenu;

	public MenuAcao(Map<String, ItemMenu> opcoesMenu) {
		this.opcoesMenu = opcoesMenu;
	}

	public void run() {
		
		BlocoMenu menuPrincipal = (BlocoMenu) getBot().obterBloco(getBlocosId().get(0));
		
		if (this.opcoesMenu.containsKey(getBot().obterValor(menuPrincipal.getVariavelOpcao()))) {
			getBlocoAcionador().setProximoBloco(opcoesMenu.get(getBot().obterValor(menuPrincipal.getVariavelOpcao())).getBlocoId());
		} else {
			getBlocoAcionador().setProximoBloco(menuPrincipal.getId());
		}
		
		getBot().adicionar(menuPrincipal.getVariavelOpcao(), "");
	}
}
