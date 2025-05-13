package core;

public class MoyenTransport implements Suspendable{
    private String identifiant;
    private boolean suspendu;

    public MoyenTransport(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getIdentifiant() { return identifiant; }    
    public String toString() { return identifiant ;}

	@Override
	public void suspendre() {
		
		if(!this.suspendu) this.suspendu = true;

	}

	@Override
	public void reactiver() {
		
		if(this.suspendu) this.suspendu = false;
		
	}

	@Override
	public boolean estSuspendu() {
		
		return this.suspendu;
	}

	@Override
	public String getEtat() {
		
		return this.suspendu ? "Suspendu" : "Active";
	}
  

}