package ai.aurora.rh;

import ai.aurora.core.Bloco;
import ai.aurora.core.BlocoMenu;
import ai.aurora.core.Bot;
import ai.aurora.core.ItemMenu;
import ai.aurora.core.MensagemUtil;
import ai.aurora.rh.modulo.FeriasModulo;
import ai.aurora.rh.modulo.HoleriteModulo;
import ai.aurora.rh.modulo.OnboardingModulo;

public class AuroraRHBot extends Bot {
	
	public AuroraRHBot() {
		
		MensagemUtil msg = MensagemUtil.obterInstancia();
		
		BlocoMenu blocoMenuPrincipal = new BlocoMenu(this);
		BlocoMenu blocoMenuFerias = new BlocoMenu(this);
		BlocoMenu blocoMenuSolicitacoes = new BlocoMenu(this);

		OnboardingModulo onboardingModulo = new OnboardingModulo(this);
		FeriasModulo moduloFerias = new FeriasModulo(this);
		HoleriteModulo moduloHolerite = new HoleriteModulo(this);
		
		Bloco encerramento = new Bloco(this);
		
		setBlocoInicial(onboardingModulo.getBlocoInicial().getId());
		
		blocoMenuPrincipal.setPergunta(msg.obter("menu_principal"));
		blocoMenuPrincipal
		         .adicionarItemMenu(new ItemMenu("1", "Ferias/Licença", blocoMenuFerias.getId()))
			     .adicionarItemMenu(new ItemMenu("2", "Holerite", moduloHolerite.getBlocoInicial().getId()))
			     .adicionarItemMenu(new ItemMenu("3", "Solicitacoes", blocoMenuSolicitacoes.getId()))
			     .adicionarItemMenu(new ItemMenu("9", "Encerrar conversa", encerramento.getId()))
			     .build();
		
		blocoMenuFerias.setPergunta(msg.obter("menu_ferias"));
		blocoMenuFerias
				 .adicionarItemMenu(new ItemMenu("1", "Ferias Parciais/Completas", moduloFerias.getBlocoInicial().getId()))
				 .adicionarItemMenu(new ItemMenu("2", "Licenças Medicas", moduloFerias.getBlocoInicial().getId()))
				 .adicionarItemMenu(new ItemMenu("3", "Day-off", moduloFerias.getBlocoInicial().getId()))
			     .adicionarItemMenu(new ItemMenu("9", "Menu Principal", blocoMenuPrincipal.getId()))
			     .build();
		
		blocoMenuSolicitacoes.setPergunta(msg.obter("menu_ferias"));
		blocoMenuSolicitacoes
				 .adicionarItemMenu(new ItemMenu("1", "Informe de Rendimentos", moduloFerias.getBlocoInicial().getId()))
				 .adicionarItemMenu(new ItemMenu("9", "Menu Principal", blocoMenuPrincipal.getId()))
			     .build();
		
		onboardingModulo.setBlocoRetorno(blocoMenuPrincipal);
		moduloFerias.setBlocoRetorno(blocoMenuFerias);
		moduloHolerite.setBlocoRetorno(blocoMenuPrincipal);
		
		encerramento.setPergunta(msg.obter("encerramento"));
		
	}

}
