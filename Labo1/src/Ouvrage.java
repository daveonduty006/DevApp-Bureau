public class Ouvrage {
	
	protected Date dateEmprunt;
	protected int num;
	//
	Ouvrage(int num) {
		this.num= num;
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
		if(this.dateEmprunt == null) {
			return null;
		}
		return dateEmprunt;
	}
	//
	public void setDateEmprunt(Date uneDate) {
		this.dateEmprunt= uneDate;
	}
	//
	@Override
	public String toString() {
		return this.num+" "+this.getDateEmprunt()+" ";
	}
	

	
	

}
