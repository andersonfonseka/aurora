package ai.blip.aurora.rh.acao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import ai.blip.aurora.core.Bloco;
import ai.blip.aurora.core.BlocoExecutor;
import ai.blip.aurora.core.Bot;
import ai.blip.aurora.rh.entidade.Ferias;
import ai.blip.aurora.rh.repositorio.FeriasRepositorio;

public class FeriasAcao extends BlocoExecutor {
	
	private Bot bot;
	
	private String sucesso, erroTempo;
	
	public FeriasAcao(Bot bot, List<String> blocosId) {
		super(bot, blocosId);

		this.sucesso = getBlocosId().get(0);
		this.erroTempo = getBlocosId().get(1);
	}

	@Override
	public void run() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Calendar dataInformada = Calendar.getInstance();
		Calendar dataAdmissao = Calendar.getInstance();
		
		FeriasRepositorio feriasRepositorio = new FeriasRepositorio();

		try {
			dataInformada.setTime(sdf.parse(String.valueOf(bot.obterValor("dataFerias"))));
			dataAdmissao.setTime(sdf.parse(String.valueOf(bot.obterValor("dataAdmissao"))));

			int yearDiff = dataInformada.get(Calendar.YEAR) - dataAdmissao.get(Calendar.YEAR);
			int monthDiff = dataInformada.get(Calendar.MONTH) - dataAdmissao.get(Calendar.MONTH);

			int totalMonths = yearDiff * 12 + monthDiff;

			bot.adicionar("tempoDecorrido", totalMonths);

			if (totalMonths >= 12) {
				
				Ferias ferias = new Ferias();
				ferias.setCpf(bot.obterValor("cpf").toString());
				ferias.setDataPrevista(String.valueOf(bot.obterValor("dataFerias")));
				
				feriasRepositorio.agendar(ferias);
				
				getBlocoAcionador().setProximoBloco(this.sucesso);
			} else {
				getBlocoAcionador().setProximoBloco(this.erroTempo);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
