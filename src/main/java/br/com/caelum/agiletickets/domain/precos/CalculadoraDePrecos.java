package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		BigDecimal preco;
		
		switch (sessao.getEspetaculo().getTipo()) {
		case CINEMA:
		case SHOW:
			preco = adicionaValorCinemaShow(sessao);
			break;
		case BALLET:
		case ORQUESTRA:
			preco = adicionaValorBalletOrchestra(sessao);
			preco = adicionaValorTempoIngresso(sessao, preco);
			break;
		default:
			preco = sessao.getPreco();
			break;
		}

		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	private static BigDecimal adicionaValorTempoIngresso(Sessao sessao,
			BigDecimal preco) {
		if(sessao.getDuracaoEmMinutos() > 60){
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		}
		return preco;
	}

	private static BigDecimal adicionaValorBalletOrchestra(Sessao sessao) {
		BigDecimal preco;
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.50) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.20)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

	private static BigDecimal adicionaValorCinemaShow(Sessao sessao) {
		BigDecimal preco;
		//quando estiver acabando os ingressos... 
		if((sessao.getTotalIngressos() - sessao.getIngressosReservados()) / sessao.getTotalIngressos().doubleValue() <= 0.05) { 
			preco = sessao.getPreco().add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
		} else {
			preco = sessao.getPreco();
		}
		return preco;
	}

}