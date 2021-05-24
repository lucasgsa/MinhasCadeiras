package bigExampleLab;
public class Anuncio {

	private String descricao;
	private AnuncioType codigoTipo;

	public Anuncio(String descricao, AnuncioType codigoTipo) {
		this.descricao = descricao;
		this.codigoTipo = codigoTipo;
	}
	public AnuncioType getCodigoTipo() {
		return codigoTipo;
	}
	public void setCodigoPreco(AnuncioType arg) {
		codigoTipo = arg;
	}
	public String getDescricao (){
		return descricao;
	}
}