import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import models.GPS;

/**
 * Lab5 PSOFT 2020.1e
 * "O aplicativo deve calcular a rota baseando-se em alguns meios de transporte, 
 * como por exemplo, Pedestre, Carro, Ônibus e Metrô. 
 * O usuário pode escolher o meio de transporte desejado e pode modificá-lo a qualquer momento. 
 * Após cada modificação do meio de transporte, o aplicativo deve recalcular a rota. 
 * Implemente o “aplicativo” em Java. Não é necessário interface gráfica nem ser um aplicativo de verdade. 
 * Implemente apenas o módulo dos requisitos citados acima com uma interface textual. 
 * O cálculo da rota pode ser abstraído por um System.out.println(“Calculando a rota de Pedestre…”);"
 * 
 * @author Lucas Gabriel Soares de Almeida - 119110385
 *
 */

public class Application {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Endereço A: ");
		String enderecoA = sc.nextLine();
		
		System.out.println("Endereço B: ");
		String enderecoB = sc.nextLine();
		
		GPS gps = new GPS(enderecoA, enderecoB);
		
		while (true) {
			String cabecalho = "\nComandos:\n"
					+ "1 - Calcular Rota\n"
					+ "2 - Definir método cálculo rota\n"
					+ "3 - Definir Endereço A\n"
					+ "4 - Definir Endereço B\n"
					+ "5 - Finalizar";
			System.out.println(cabecalho);
			String command = sc.nextLine();
			if (command.equals("1")) {
				if (!gps.possuiRota()) System.out.println("Não foi cadastrado nenhum método de rota.");
				else System.out.println(gps.calcularRota());
			}
			
			else if (command.equals("2")){
				String[] met = {"1", "2", "3", "4"};
				List<String> metodos = Arrays.asList(met);
				String cabecalhoMetodos = "\nMetodos:\n"
						+ "1 - Carro\n"
						+ "2 - Metro\n"
						+ "3 - Onibus\n"
						+ "4 - Pedestre";
				System.out.println(cabecalhoMetodos);
				String command2 = sc.nextLine();
				if (metodos.contains(command2)) {
					System.out.println(gps.setRouteStrategy(Integer.parseInt(command2)));
				}
				else System.out.println("Método inválido");
			}
			
			else if (command.equals("3")){
				String endereco = sc.nextLine();
				gps.setEnderecoA(endereco);
			}
			
			else if (command.equals("4")){
				String endereco = sc.nextLine();
				gps.setEnderecoB(endereco);
			}
			
			else if (command.equals("5")) {
				break;
			}
		}
		
	}
}
