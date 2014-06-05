package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class CriadorDeSessoesDiaria implements CriadorDeSessoes {

	@Override
	public List<Sessao> cria(LocalDate inicio, LocalDate fim,
			LocalTime horario, Espetaculo espetaculo) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		Sessao sessao = new Sessao();
		for (LocalDate data=inicio; !data.isAfter(fim); data=data.plusDays(1)) {
			sessao.setInicio(inicio.toDateTime(horario));
			sessao.setEspetaculo(espetaculo);
			sessoes.add(sessao);
			
		}
		return sessoes;
	}

}
