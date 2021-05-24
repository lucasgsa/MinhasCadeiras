package models;

public interface Rota {
	
	public static int Carro = 1;
	public static int Metro = 2;
	public static int Onibus = 3;
	public static int Pedestre = 4;
	
	public String calcularRota(String enderecoA, String enderecoB);
}
