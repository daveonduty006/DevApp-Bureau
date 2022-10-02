
public class Noeud {
	
	
	
	//
	
	private Object data;
	private Noeud suiv, prec;
	
	//
	
	Noeud(Object data) {
		this.data= data;
		this.suiv= null;
		this.prec= null;
	}
	
	//
	
	public Object getData() {
		return this.data;
	}
	
	public Noeud getSuiv() {
		return this.suiv;
	}
	
	public Noeud getPrec() {
		return this.prec;
	}
	
	public void setData(Object data) {
		this.data= data;
	}
	
	public void setSuiv(Noeud suiv) {
		this.suiv= suiv;
	}
	
	public void setPrec(Noeud prec) {
		this.prec= prec;
	}
	
}
