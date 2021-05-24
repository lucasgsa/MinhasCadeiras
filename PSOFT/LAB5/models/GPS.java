package models;

import Strategy.Carro;
import Strategy.Metro;
import Strategy.Onibus;
import Strategy.Pedestre;

public class GPS {
	private String enderecoA;
	private String enderecoB;
	private Rota rota;
	
	public GPS(String enderecoA, String enderecoB) {
		this.enderecoA = enderecoA;
		this.enderecoB = enderecoB;
	}
	
	public String getEnderecoA() {
		return enderecoA;
	}

	public void setEnderecoA(String enderecoA) {
		this.enderecoA = enderecoA;
	}

	public String getEnderecoB() {
		return enderecoB;
	}

	public void setEnderecoB(String enderecoB) {
		this.enderecoB = enderecoB;
	}
	
	public boolean possuiRota() {
		return rota != null;
	}

	public String setRouteStrategy(int strategy) {
		if (strategy == Rota.Carro) rota = new Carro();
		if (strategy == Rota.Metro) rota = new Metro();
		if (strategy == Rota.Onibus) rota = new Onibus();
		if (strategy == Rota.Pedestre) rota = new Pedestre();
		return calcularRota();
	}
	
	public String calcularRota() {
		return rota.calcularRota(enderecoA, enderecoB);
	}
	
}
