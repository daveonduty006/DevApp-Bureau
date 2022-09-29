
public class ListeChainee {
	
	
	
	//
	
	private Noeud tete;
	private Noeud queue;
	
	//
	
	ListeChainee() {
		tete= null;
		queue= null;
	}
	
	//
	
	public Noeud getTete() {
		return this.tete;
	}
	
	public Noeud getQueue() {
		return this.queue;
	}
	
	public void setTete(Object data) {
		if(this.tete == null) {
			this.tete= new Noeud(data, null, null);
			this.queue= this.tete;
		}else {
			Noeud nouveauNoeud= new Noeud(data, this.tete, null);
			this.tete.setPrec(nouveauNoeud);
			this.tete= nouveauNoeud;
		}
	}
	
	public void setQueue(Object data) {
		if(this.tete == null) {
			this.tete= new Noeud(data, null, null);
			this.queue= this.tete;
		}else {
			Noeud nouveauNoeud= new Noeud(data, null, this.queue);
			this.queue.setSuiv(nouveauNoeud);
			this.queue= nouveauNoeud;
		}
	}

}
