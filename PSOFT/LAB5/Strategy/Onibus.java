package Strategy;

import models.Rota;

public class Onibus implements Rota {

	@Override
	public String calcularRota(String enderecoA, String enderecoB) {
		return "Calculando rota de Onibus de "+enderecoA+" até "+enderecoB+" ...";
	}
	
}
