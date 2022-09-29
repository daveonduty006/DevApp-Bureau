public class Ouvrage {
	
	protected Date dateEmprunt;
	protected int num;
	
	//
	
	Ouvrage(){
		
	}

	Ouvrage(int num, Date dateEmprunt) {
		this.num= num;
		this.dateEmprunt= dateEmprunt;
	}
	
	//
	public int getNum() {
		return this.num;
	}
	
	public Date getDateEmprunt() {
		return this.dateEmprunt;
	}
	
	//
	
	public void setDateEmprunt(Date uneDate) {
		this.dateEmprunt= uneDate;
	}
	
	//
	
	@Override
	public String toString() {
		String rep= (this.dateEmprunt == null) ?
			"Ouvrage no."+this.num+"\n"+"Date d'Emprunt: disponible\n"
		:
			"Ouvrage no."+this.num+"\n"+"Date d'Emprunt: "+this.dateEmprunt+"\n";
		return rep;
		
	}
	

	
	

}
