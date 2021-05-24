package bigExampleLab;
class Compra {
	private Anuncio anuncio;
	private int diasAnuncio;
	
	public Compra(Anuncio anuncio, int diasAnuncio) {
		this.anuncio = anuncio;
		this.diasAnuncio = diasAnuncio;
	}
	public int getDiasAnuncio() {
		return diasAnuncio;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	
	public double total() {
		double totalParcial = 0;
		
		switch (getAnuncio().getCodigoTipo()) {
		case IMAGEM:
			totalParcial += 2;
			if (getDiasAnuncio() > 2)
				break;
		case VIDEO:
			totalParcial += getDiasAnuncio() * 3;
			break;
		case TEXTO:
			totalParcial += 1.5;
			if (getDiasAnuncio() > 3)
				totalParcial += (getDiasAnuncio() - 3) * 1.5;
			break;
		}
		
		return totalParcial;
	}
	
	public int pontosFRequentes() {
		if ((getAnuncio().getCodigoTipo() == AnuncioType.VIDEO) && getDiasAnuncio() > 1) return 2;
		return 1;
	}
	
	@Override
	public String toString() {
		return "\t" + getAnuncio().getDescricao() + "\t" + String.valueOf(total()) + "\n";
	}
	
	
}