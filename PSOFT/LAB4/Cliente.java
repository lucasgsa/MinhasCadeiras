package bigExampleLab;

import java.util.Vector;

class Cliente {
	private String nome;
	private Vector<Compra> compras = new Vector<Compra>();

	public Cliente(String nome) {
		this.nome = nome;
	}

	public void addCompra(Compra arg) {
		compras.addElement(arg);
	}

	public String getNome() {
		return nome;
	}
	
	public String historico() {
		
		String resultado = "Historico de compras de anuncios por " + getNome() + "\n";
		
		resultado += toStringCompras();
		
		resultado += "Total devido é " + String.valueOf(total()) + "\n";
		resultado += "Voce ganhou " + String.valueOf(pontosFRequentes()) + " pontos";
		
		return resultado;
	}
	
	private String toStringCompras() {
		String resultado = "";
		for (Compra compra: compras) {
			resultado += compra.toString();
		}
		return resultado;
	}
	
	private int pontosFRequentes() {
		int pontosFRequentes = 0;
		for(Compra compra:compras) {
			pontosFRequentes += compra.pontosFRequentes();
		}
		return pontosFRequentes;
	}
	
	private double total() {
		double total = 0;
		for (Compra compra:compras) {
			total += compra.total();
		}
		return total;
	}

}