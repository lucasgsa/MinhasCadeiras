package Strategy;

import models.Rota;

public class Carro implements Rota {

	@Override
	public String calcularRota(String enderecoA, String enderecoB) {
		return "Calculando rota de Carro de "+enderecoA+" até "+enderecoB+" ...";
	}
	
}
