import java.util.ArrayList;

public class BNF {
	String nterminalId;
	ArrayList<String> r = new ArrayList<String>();
	ArrayList<String> nterminais = new ArrayList<String>();
	ArrayList<String> alfabeto = new ArrayList<String>();
	
	public BNF() {
		
	}

	public BNF(String nterminalId, String inicial, ArrayList<String> nterminais, ArrayList<String> alfabeto, ArrayList<String> r) {
		super();
		this.r = r;
		this.nterminalId = nterminalId;
		this.nterminais = nterminais;
		this.alfabeto = alfabeto;
	}

	public ArrayList<String> getR() {
		return r;
	}

	public void setR(ArrayList<String> r) {
		this.r = r;
	}

	public String getNterminalId() {
		return nterminalId;
	}

	public void setNterminalId(String nterminalId) {
		this.nterminalId = nterminalId;
	}

	public ArrayList<String> getNterminais() {
		return nterminais;
	}

	public void setNterminais(ArrayList<String> nterminais) {
		this.nterminais = nterminais;
	}

	public ArrayList<String> getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(ArrayList<String> alfabeto) {
		this.alfabeto = alfabeto;
	}
	
}
