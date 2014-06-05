package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	
	DIARIA{
		public CriadorDeSessoes getCriadorDeSessoes(){
			return new CriadorDeSessoesDiaria();
		}
	},
	SEMANAL{
		public CriadorDeSessoes getCriadorDeSessoes(){
			return new CriadorDeSessoesSemanal();
		}
	};
	public abstract CriadorDeSessoes getCriadorDeSessoes();
}
