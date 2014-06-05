package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;


public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	
	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void DeveCriarUmaSessaoParaDatasDeInicioEFimIguaisDiaria() {
		Espetaculo diario = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim= new LocalDate();
		Periodicidade diaria = Periodicidade.DIARIA;
		
		LocalTime hora = new LocalTime("20:00");
		List<Sessao> sessoes = diario.criaSessoes(inicio, fim, hora, diaria);
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(diario, sessoes.get(0).getEspetaculo());
		}
	
	
	@Test
	public void DeveCriarUmaSessaoParaDataDeinicioEfimSemanal() {
		Espetaculo semanal = new Espetaculo();
		LocalDate inicio =new LocalDate();
		LocalDate fim= new LocalDate();
		Periodicidade periodo = Periodicidade.SEMANAL;
		
		LocalTime hora = new LocalTime("20:00");
		List<Sessao> sessoes = semanal.criaSessoes(inicio, fim, hora, periodo);
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(semanal, sessoes.get(0).getEspetaculo());
		}
		
	@Test
	public void DeveCriaVariasSessaoParaIntervaloDeDiasDiariamente() {
		Espetaculo semanal = new Espetaculo();
		LocalDate inicio =new LocalDate();
		LocalDate fim= new LocalDate().plusDays(3);
		Periodicidade periodo = Periodicidade.DIARIA;
		
		LocalTime hora = new LocalTime("20:00");
		List<Sessao> sessoes = semanal.criaSessoes(inicio, fim, hora, periodo);
		Assert.assertEquals(4, sessoes.size());
		Assert.assertEquals(semanal, sessoes.get(0).getEspetaculo());
		}
	
	@Test
	public void DeveCriarVariasSessaoParaIntervaloDeDiasSemanalmente() {
		Espetaculo semanal = new Espetaculo();
		LocalDate inicio =new LocalDate();
		LocalDate fim= new LocalDate().plusDays(8);
		Periodicidade periodo = Periodicidade.SEMANAL;
		
		LocalTime hora = new LocalTime("20:00");
		List<Sessao> sessoes = semanal.criaSessoes(inicio, fim, hora, periodo);
		Assert.assertEquals(2, sessoes.size());
		Assert.assertEquals(semanal, sessoes.get(0).getEspetaculo());
		}
	
	
	
	
}
