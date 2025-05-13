package core;
import java.util.*;

public class ServiceReclamation {
    private  final int SEUIL = 3;
    private  Map<TypeReclamation,TreeSet<Reclamation>> reclamationsParType = new TreeMap<>();
    private  Map<Personne, TreeSet<Reclamation>> reclamationsParPersonne = new HashMap<>();
    private  Map<Suspendable, TreeSet<Reclamation>> reclamationsParSuspendable = new HashMap<>();

   public void soumettre(Reclamation reclamation) {
	   TreeSet<Reclamation> tr1 = this.reclamationsParType.get(reclamation.getType());
	   	if(tr1 == null) {
	   		// if the tree is empty, we create one, update its reference in the map then add the elements to it 
	   		tr1 = new TreeSet<>();
	   		this.reclamationsParType.put(reclamation.getType(), tr1);
	   	}
	   	tr1.add(reclamation);
	   	//same logic applies for all other Maps
	   	// if no tree{create tree, update reference}  add element
	   	TreeSet<Reclamation> tr2 = this.reclamationsParPersonne.get(reclamation.getPersonne());
	   	if(tr2 == null) {
	   		tr2 = new TreeSet<>();
	   		this.reclamationsParPersonne.put(reclamation.getPersonne(), tr2);	
	   	}
	   	tr2.add(reclamation);

	   	TreeSet<Reclamation> tr3 = this.reclamationsParSuspendable.get(reclamation.getCible());
	   	if(tr3 == null) {
	   		tr3 = new TreeSet<>();
	   		this.reclamationsParSuspendable.put(reclamation.getCible(), tr3);
	   	}
	   	tr3.add(reclamation);

	    if (tr3.size() >= SEUIL) {
	    	// we don't verify if the "cible" is suspended since we already verify its state in the function
	        reclamation.getCible().suspendre();
	    }

	}
	 // I just want to look fancy, please accept it
	Optional<TreeSet<Reclamation>> getTreeByPersonne(Personne p){
		return Optional.ofNullable(this.reclamationsParPersonne.get(p));
	}
	
	public void afficherReclamations(Personne personne) {
		// we use the optional, to let you know that the getter may return a null and you have to deal with it
        Optional<TreeSet<Reclamation>> reclamationsOptionals = getTreeByPersonne(personne);
        // optionals can be seen as boxes in which we return the target if it is found or null if not
        // we use orElse to return the object we want, or return an alternative if 
        TreeSet<Reclamation> reclamations = reclamationsOptionals.orElse(new TreeSet<>());
        if(reclamations.size() == 0) {
        	System.out.println("Aucune réclamation pour " + personne.toString());
        } else {
        	reclamations.forEach(r -> System.out.println(r.toString()));
        }

	}
	
	// we could use an optional here too, but let's keep it simple 
	public void afficherReclamations(Suspendable suspendable) {
        TreeSet<Reclamation> reclamations = reclamationsParSuspendable.getOrDefault(suspendable, new TreeSet<>());
        if (reclamations.isEmpty()) {
            System.out.println("Aucune réclamation pour " + suspendable);
        } else {
            for (Reclamation r : reclamations) {
                System.out.println(r);
            }
        }
	}
	
	public void afficherReclamations() {
		this.reclamationsParType.forEach((key, value) -> {
			System.out.println("Reclamation de type "+ key);
			for(Reclamation rec: value) {
				System.out.println(rec.toString());
			}
		});
			
	}

	public void resoudre(Reclamation reclamation) {
        reclamationsParType.getOrDefault(reclamation.getType(), new TreeSet<>()).remove(reclamation);
        reclamationsParPersonne.getOrDefault(reclamation.getPersonne(), new TreeSet<>()).remove(reclamation);
        TreeSet<Reclamation> suspendableReclamations = reclamationsParSuspendable.getOrDefault(reclamation.getCible(), new TreeSet<>());
        suspendableReclamations.remove(reclamation);

        if (reclamationsParType.get(reclamation.getType()).isEmpty()) {
            reclamationsParType.remove(reclamation.getType());
        }
        if (reclamationsParPersonne.get(reclamation.getPersonne()).isEmpty()) {
            reclamationsParPersonne.remove(reclamation.getPersonne());
        }
        if (suspendableReclamations.isEmpty()) {
            reclamationsParSuspendable.remove(reclamation.getCible());
        }

        if (suspendableReclamations.size() < SEUIL && reclamation.getCible().estSuspendu()) {
            reclamation.getCible().reactiver();
        }
		
	}
	

    
}