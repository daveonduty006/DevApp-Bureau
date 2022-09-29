
public class Noeud {
	
	
	
	//
	
	private Object data;
	private Noeud suiv, prec;
	
	//
	
	Noeud() {
		this.data= null;
		this.suiv= null;
		this.prec= null;
	}
	
	Noeud(Object data, Noeud suiv, Noeud prec) {
		this.data= data;
		this.suiv= suiv;
		this.prec= prec;
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
	
	//
	
	@Override
	public String toString() {
		String dataSuiv= (this.suiv == null) ? null : this.suiv.getData().toString();
		String dataPrec= (this.prec == null) ? null : this.prec.getData().toString();
		return "Node[data= "+this.data+", suiv= "+dataSuiv+", prec= "+dataPrec+"]";
	}
	
}
