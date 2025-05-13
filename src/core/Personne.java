package core;

public abstract class Personne{
 
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    public String getName() {
    	return this.prenom;
    }
    public String getLastName() {
    	return this.nom;
    }
    @Override
    public String toString() {
        return prenom + " " + nom;
    }
    @Override
    public boolean equals(Object obj) {
    	return this.nom.compareTo(((Personne)obj).getLastName()) + this.prenom.compareTo(((Personne)obj).getName()) == 0; 
    }
    @Override
    
    public int hashCode() {
    	return this.nom.hashCode() + this.prenom.hashCode();
    }
}
