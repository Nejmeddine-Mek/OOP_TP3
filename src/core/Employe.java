package core;
public class Employe extends Personne{
    private final String matricule;
    private final Fonction fonction;

    public Employe(String nom, String prenom, String matricule, Fonction fonction) {
        super(nom, prenom);
        this.matricule = matricule;
        this.fonction = fonction;
    }

    public String getMatricule() { return matricule; }
    public Fonction getFonction() { return fonction; }

}