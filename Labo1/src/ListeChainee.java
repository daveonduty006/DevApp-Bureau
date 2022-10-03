
public class ListeChainee {
	
	
	
	//
	
	private Noeud tete;
	private Noeud queue;
	private int taille;
	
	//
	
	ListeChainee() {
		tete= null;
		queue= null;
		this.taille= 0;
	}
	
	//
	
	public Noeud getTete() {
		return this.tete;
	}
	
	public Noeud getQueue() {
		return this.queue;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	//
	
	public Noeud obtenir(Object obj) {
		Noeud pointeurTete= this.tete;
        while(pointeurTete.getData() != null) {
            if(pointeurTete.getData() == obj) {
                return pointeurTete; // Pour sortir de la boucle
            }else {
                pointeurTete= pointeurTete.getSuiv();
            }
        }
        return pointeurTete;
	}
	
    public void retirer(Object obj) {
        Noeud noeudObj = obtenir(obj);
        if(noeudObj == null) {
            System.out.println("Noeud vide");
        }else if(noeudObj == this.tete) { // Cas si on supprime le premier noeud
        	noeudObj.getSuiv().setPrec(noeudObj.getSuiv());
            this.tete= this.tete.getSuiv();
        }else if(noeudObj == this.queue) { // Cas où on supprime le dernier noeud
            noeudObj.getPrec().setSuiv(noeudObj.getSuiv());
            this.queue= this.queue.getPrec();
        }else { // Cas où on supprime un élément autre que le premier ou dernier noeud
            noeudObj.getPrec().setSuiv(noeudObj.getSuiv());
            noeudObj.getSuiv().setPrec(noeudObj.getPrec());
        }
        this.taille--;
    }
	
	public void ajouter(Object obj) {
        Noeud unNoeud= new Noeud(obj);
        if(this.tete == null) { // Liste vide
        	unNoeud.setSuiv(unNoeud);
        	unNoeud.setPrec(unNoeud);
            this.tete= unNoeud;
            this.queue= unNoeud;
        }else {
            this.queue.setSuiv(unNoeud);
            unNoeud.setPrec(this.queue);
            this.queue= unNoeud;
        }
        this.taille++;
    }

}
