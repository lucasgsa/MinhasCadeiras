package Strategy;

import models.Rota;

public class Pedestre implements Rota {

	@Override
	public String calcularRota(String enderecoA, String enderecoB) {
		return "Calculando rota de Pedestre de "+enderecoA+" até "+enderecoB+" ...";
	}
	
}
