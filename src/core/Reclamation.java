package core;
import java.time.LocalDate;

public class Reclamation implements Comparable <Reclamation>{
    private static int compteur = 1; 
    private int numero;
    private LocalDate date;
    private Personne personne;
    private TypeReclamation type;
    private Suspendable cible;
    private String description;


    public Reclamation(Personne personne, TypeReclamation type, Suspendable cible, String description, LocalDate date) {
        this.personne = personne;
        this.type = type;
        this.cible = cible;
        this.description = description;
        this.date = date;
        this.numero = compteur;
        compteur++;
    }

    public Suspendable getCible() { return cible; }
    public int getNumero() { return numero; }
    public LocalDate getDate() { return date; }
    public Personne getPersonne() { return personne; }
    public String getDescription() { return description; }
    public TypeReclamation getType() {return type;}
    public String toString(){
    	StringBuilder complaint =  
    	new StringBuilder("Reclamation #"+this.numero+"\nDate: "+this.date+" | Type: "+this.type +" | Cible: "+this.cible +" | Descritption: "+this.description +" | Soumise par: "+this.personne.toString());
		return complaint.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
    	return this.personne.equals(((Reclamation)obj).getPersonne()); 
    }
    @Override
    
    public int hashCode() {
    	return this.personne.hashCode();
    }

	@Override
	public int compareTo(Reclamation o) {
		if(this.type == o.getType()) return this.date.compareTo(o.getDate());
		else {
			return this.type.compareTo(o.getType());
		}
	}
      
    }
