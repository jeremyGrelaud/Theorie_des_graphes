package Theorie_graphe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



/* exemple de fichier a lire
4  = nbr de sommets
5  = nbr d'arcs
3 1 25  tous les arcs sous la forme  sommet_départ  sommet d'arrivé  valeur de l'arc
1 0 12
2 0 -5
0 1 0
2 1 7
*/


public class Graphe {
	private List<Sommet> sommets; /*liste des differents sommets du graphe*/
	private int nbr_arcs;
	private int nbr_sommets;
	
	public Graphe() {
		this.sommets = new ArrayList<Sommet>();
		this.nbr_arcs = 0; //quand on initialise on ne sait pas encore on le remplira plus tard en lisant le fichier
		this.setNbr_sommets(0); //on ne sait pas avant la lecture du fichier
	}
	public Graphe(final Graphe graphe) { //constructeur de copie
	
		List<Sommet> nouvelle_liste = new ArrayList<Sommet>(); //la nouvelle liste des sommets ou on va les copier
			for(int i=0; i<graphe.sommets.size();i++) { //on regarde la taille de la liste des sommets  = nbr de sommets du graphe
				Sommet new_sommet = new Sommet(graphe.sommets.get(i));
				nouvelle_liste.add(new_sommet);
			}
		this.sommets = nouvelle_liste;

		this.nbr_arcs = graphe.getNbArcs();
		this.setNbr_sommets(graphe.sommets.size());
		
	}
	
	
	public int getNbArcs() {
		return nbr_arcs;
	}

	public void setNbArcs(int nbr_arcs) {
		this.nbr_arcs = nbr_arcs;
	}

	

	public List<Sommet> getSommets() {
		return this.sommets;
	}

	public void setSommets(final List<Sommet> sommets) {
		List<Sommet> nouvelle_liste_sommets = new ArrayList<Sommet>();
		for(int i=0; i<this.sommets.size();i++) {
			nouvelle_liste_sommets.add(this.sommets.get(i));
		}
		this.sommets = nouvelle_liste_sommets;
	}
	
	public int getNbr_sommets() {
		return nbr_sommets;
	}
	public void setNbr_sommets(int nbr_sommets) {
		this.nbr_sommets = nbr_sommets;
	}

	public boolean containsSommets(final String la_chaine) {  //faut check les nom des sommets de la liste et voir si c'est dedan
		for (Sommet sommet : getSommets()){ //pour chaque sommet dans la liste des sommets
			if(sommet.getNom().equals(la_chaine)) {
				return true;
			}
		}//si aucun des sommets dans la liste ne correspondait à celui recherché on retourne faux
		return false; 
	}
	
	//fonction simmilaire à la précédente sauf que cette fois on retourne un Sommet au lieu d'un booléen
	//on parcours la liste jusqu'a trouver l'etat correspondant
    public Sommet getSommetByNom(String id) {
        for (Sommet som : this.sommets) { //pour chaque sommet du graphe
            if (som.getNom().equals(id)) { //si le nom correspond au sommet recherché
                return som; //on retourne ce sommet
            }
        }
        
        return null; // si le sommet n'est pas dans le graphe on retourne null
    }
	
	
	public void afficher_graphe() {
		
		Graphe graphe = new Graphe(this);
		
		
		System.out.print("La liste des sommets : ");
		for(int i=0; i<graphe.sommets.size();i++) {
				System.out.print(graphe.getSommets().get(i).getNom()+" ");
		} 
		//Affichage des arcs
		System.out.println("");
		System.out.print("Il y a " + getNbArcs() + " arcs :");
		System.out.println("");

		 for (Sommet sommet : this.sommets) { //pour chaque sommet dans la liste
		    for(String clef : sommet.getArc().keySet()) { //string avec toutes les clefs
		    	/*if(clef.equals("")) {
		    		System.out.println(sommet.getNom() +" * " +Arrays.toString(sommet.getArc().get(clef).toArray()));
		    	}*/
		    	//else {
		    		System.out.println(sommet.getNom() + " " +Arrays.toString(sommet.getArc().get(clef).toArray()) + " valeur : " +clef);
		    	//}
		    	
		    }
		    		
		    
	     }

	}
	
	
	
	public void programme() {
		Graphe graphe_depart = new Graphe(this);
		
		Scanner sc = new Scanner(System.in);
		String mot = "";
		
		System.out.println("");
		System.out.println("Pour arrêter l'execution taper EXIT");
		do {
			System.out.println("");
			System.out.println("Veuillez taper un chiffre :");
			System.out.println("1 : Chemin le plus court ");
			System.out.println("2 : affichage");
			
			mot = sc.nextLine();
			
			
			while(!( (mot.equals("1")) || (mot.equals("2")) || (mot.equals("EXIT")) )) {
				System.out.println("");
				System.out.println("Veuillez faire un choix valide ");
				System.out.println("Veuillez taper un chiffre :");
				System.out.println("1 : Chemin le plus court ");
				System.out.println("2 : affichage");
				
				mot = sc.nextLine();
				
			}
			
			if(mot.equals("1")){
				
				System.out.println("Rien : ");
				
			}
			if(mot.equals("2")){
				System.out.println("Affichage : ");	
				graphe_depart.afficher_graphe();
						
			}
			
		}while(!mot.equals("EXIT"));
		//fin
		//System.out.println("Fin du programme");
		//sc.close();
		//on ne peut pas fermer le scanner ici sinon on n'a plus de flux dans la fonction execution
		//on ferme donc le scanner dans execution
		
	}

}