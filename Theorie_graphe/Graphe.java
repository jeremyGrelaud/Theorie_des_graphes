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
	private int nbr_arcs;
	private int nbr_sommets;
	private int[][] matrice_adjacence;
	private int[][] matrice_valeurs;
	
	
	public Graphe() {
		this.nbr_arcs = 0; //quand on initialise on ne sait pas encore on le remplira plus tard en lisant le fichier
		this.setNbr_sommets(0); //on ne sait pas avant la lecture du fichier
		
	}
	public Graphe(final Graphe graphe) { //constructeur de copie
	
		this.nbr_arcs = graphe.getNbArcs();
		this.setNbr_sommets(graphe.getNbr_sommets());
		this.setMatrice_adjacence(graphe.getMatrice_adjacence());
		this.setMatrice_valeurs(graphe.getMatrice_valeurs());
		
	}
	
	
	public int getNbArcs() {
		return nbr_arcs;
	}

	public void setNbArcs(int nbr_arcs) {
		this.nbr_arcs = nbr_arcs;
	}

	public int getNbr_sommets() {
		return nbr_sommets;
	}
	public void setNbr_sommets(int nbr_sommets) {
		this.nbr_sommets = nbr_sommets;
	}

	
	public void afficher_graphe() {
		
		Graphe graphe = new Graphe(this);
		
		
		System.out.print("La liste des sommets : ");
		for(int i=0; i<graphe.getNbr_sommets();i++) {
				System.out.print(i+" ");
		} 
		//Affichage des arcs
		System.out.println("");
		System.out.print("Il y a " + getNbArcs() + " arcs :");
		System.out.println("");
		

		//Affichage de la matrice d'adjacence
		System.out.println("MATRICE D ADJACENCE");
		
		System.out.print("              ");
		for(int i=0 ; i<this.getMatrice_adjacence().length; i++) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		
		
		System.out.print("              ");
		for(int i=0 ; i<this.getMatrice_adjacence().length; i++) {
			System.out.print("- ");
		}
		System.out.println(" ");
		
		for(int i=0 ; i<this.getMatrice_adjacence().length; i++) {
			System.out.print(" sommet n°" + i  +" | ");
			for(int j=0 ; j<this.getMatrice_adjacence().length; j++) {
				System.out.print(this.matrice_adjacence[i][j] + " ");
			}
			System.out.println(" ");
		}
		
		
		
		
		//Affichage de la matrice des valeurs
		System.out.println("MATRICE DES VALEURS");
		
		System.out.print("              ");
		for(int i=0 ; i<this.getMatrice_valeurs().length; i++) {
			System.out.print(i+"   ");
		}
		System.out.println(" ");
		
		
		System.out.print("              ");
		for(int i=0 ; i<this.getMatrice_valeurs().length; i++) {
			System.out.print("-   ");
		}
		System.out.println(" ");
		
		for(int i=0 ; i<this.getMatrice_valeurs().length; i++) {
			System.out.print(" sommet n°" + i  +" | ");
			for(int j=0 ; j<this.getMatrice_valeurs().length; j++) {
				System.out.print(this.matrice_valeurs[i][j] + "   ");
			}
			System.out.println(" ");
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
	public int[][] getMatrice_adjacence() {
		return matrice_adjacence;
	}
	public void setMatrice_adjacence(int[][] matrice_adjacence) {
		this.matrice_adjacence = matrice_adjacence;
	}
	public int[][] getMatrice_valeurs() {
		return matrice_valeurs;
	}
	public void setMatrice_valeurs(int[][] matrice_valeurs) {
		this.matrice_valeurs = matrice_valeurs;
	}

}