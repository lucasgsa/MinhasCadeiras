package Strategy;

import models.Rota;

public class Metro implements Rota {

	@Override
	public String calcularRota(String enderecoA, String enderecoB) {
		return "Calculando rota de Metro de "+enderecoA+" até "+enderecoB+" ...";
	}
	
}
