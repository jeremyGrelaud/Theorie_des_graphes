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
		
		
		
		
        for (int[] ligne : this.getMatrice_valeurs()) {
        	// converti chaque ligne en string
            //puis print 1 a 1 les lignes
            System.out.println(Arrays.toString(ligne));
        }
 
        
        
        /*tests pour représenter l'infini*/
        /*
         double inf = Double.POSITIVE_INFINITY;
         int IntValue = (int) inf;
         if(inf>5) {
        	 System.out.println(inf);
        	 System.out.println(IntValue);
         }
         
         if(IntValue>4) {
        	 System.out.println("vrai");
        	
         }*/ 
        //non utilisé car engendre des problèmes  infini+4 = -infini+4 car on ne peut pas dépasser cette valeur inforatiquement
        
            
		
	}
	
	public String obtenirChemin(int[][]matrice_des_chemins, int depart, int arrivee) { //affiche le chemin minimal d'un point de départ vers un point d'arrivée
		//fonction recursive
		String le_chemin = String.valueOf(depart);
		
		if(matrice_des_chemins[depart][arrivee] != depart) { //cela veut dire qu'il existe un chemin avec un poids plus court passant par un sommet intermédiare ou plus
			le_chemin = obtenirChemin(matrice_des_chemins,depart,matrice_des_chemins[depart][arrivee]);
		}
		
		return le_chemin + " " + String.valueOf(arrivee); //iteration ou nous arrivons à la fin du chemin
	}
	
	public void afficheLesChemins(int[][]matrice_des_chemins) { //affiche tous les chemins les plus courts entre tous les points du graphe
		
		int taille = matrice_des_chemins.length;
		for(int i=0; i<taille;i++) {
			for(int j=0; j<taille;j++) {
				if((i != j) && (matrice_des_chemins[j][i] != -1)) {
					System.out.println("Chemin le plus court du sommet "+ j + " vers "+ i + ": (" + obtenirChemin(matrice_des_chemins, j, i) + ")");
				}
			}
		}
	}
	    /*La matrice des distances c'est L dans l'algo du cours et celle des chemins c'est P*/
	public void floydWarshall(int matrice_des_distances[][]) {
		//calcule puis affiche tous les plus courts chemins du graphe à partir de la matrice initiale des distances
		//avec dans la case [i][j] le poids de l'arc du sommet i vers j directement   et 0 dans la diagonale car le poids pour aller d'un sommet vers lui même sera considéré égal à 0 quand il n'existera pas d'arc
		//et une valeur représentant l'infini pour les poids inconnu au debut
		
		
		int taille = matrice_des_distances.length;
		
		int[][]matrice_des_chemins = new int[taille][taille];
		//on initialise toutes les cases à -1
		for(int i=0; i<taille; i++) {
			for(int j=0; j<taille; j++) {
				matrice_des_chemins[i][j] = -1;
			}
		}
		
		//on a besoin de définir l'infini comme on l'a défini dans la matrice des distances
		/*double inf = Double.POSITIVE_INFINITY;
        int infini = (int) inf;*/
        int infini = 1000;
        
		//remplissage de certaines cases de la matrice par rapport aux arcs initiaux qui sont les arcs allant directement du sommet a vers sommet b
		for(int depart=0; depart<taille; depart++) {
			for(int arrivee=0; arrivee<taille; arrivee++) {
				if( (depart != arrivee) && (matrice_des_distances[depart][arrivee] != infini) ) {
					//1ere condition exclu un chemin d'un sommet vers lui même
					//2eme condition si le poids n'est pas infini dans la matrice des distances c'est qu'un arc existe entre depart et arrivee
					matrice_des_chemins[depart][arrivee] = depart;
				}
			}
		}
		
		
		//on parcours ensuite toutes les cases de la matrice
		//pour avoir tous les sommets de départ et d'arrivée
		//plus les sommets intermédiaires
		for(int inter=0; inter<taille; inter++) {
			for(int depart=0; depart<taille; depart++) {
				for(int arrivee=0; arrivee<taille; arrivee++) {
					if( matrice_des_distances[depart][inter] + matrice_des_distances[inter][arrivee] < matrice_des_distances[depart][arrivee]) { //si il existe un chemin intermédiaire moins couteux en poids
						matrice_des_distances[depart][arrivee] = matrice_des_distances[depart][inter] + matrice_des_distances[inter][arrivee]; //on attribut ce nouveau poids moins élevé
						matrice_des_chemins[depart][arrivee] = matrice_des_chemins[inter][arrivee];	 //et on update la table des chemins pour renseigner le chemin intermédiaire emprunté
					}
					
				}
				
				System.out.println("MATRICE L");
				this.printmatrice(matrice_des_distances);
				
				System.out.println("MATRICE P");
				this.printmatrice(matrice_des_chemins);
				
				if( matrice_des_distances[depart][depart] < 0) {
					 System.out.println("Circuit absorbant détecté");
		                return;
				 }
		               
			}
			
		}
		
		System.out.println("Aucun circuit absorbant détecté");
		
		//on fait appel à la fonction d'affichage de tous les chemins
		afficheLesChemins(matrice_des_chemins);	
	}
	
	public void printmatrice(int[][]matrice) {
		 for(int i=0 ; i<matrice.length; i++) {
				System.out.print("n°" + i  +" | ");
				System.out.print("\t");
				for(int j=0 ; j<matrice.length; j++) {
					System.out.print(matrice[i][j] + "\t");
				}
				System.out.println(" ");
			}
		 System.out.println(" ");
		 System.out.println(" ");
	}
	
	public void test() {

		/*double inf = Double.POSITIVE_INFINITY;
		int infini = (int) inf;*/
		int infini = 1000;
		
		/*La matrice d'adjacence :
		 * {0,0,1,0}
		 * {1,0,1,0}
		 * {0,0,0,1}
		 * {0,1,0,0}
		 * */
				    
		//matrice des distances initiale
		/*
	    int[][] distance = 
	    	{{0, infini, -2, infini},
	        {4, 0, 3, infini},
	        {infini, infini, 0, 2},
	        {infini, -1, infini, 0}};	*/   
	    
	    int[][] distance = this.creation_matrice_L_initiale();
			
	    this.floydWarshall(distance);
	}
	   
	public int[][] creation_matrice_L_initiale(){
		
		int infini = 1000;
		int[][] mat_adj = this.getMatrice_adjacence();
		int[][] mat_val = this.getMatrice_valeurs();
		int taille = mat_adj.length;
		int[][] mat_L = new int[taille][taille];
		
		for(int i=0; i<taille; i++) {
			for(int j=0; j<taille; j++) {
				if(i == j) {
					mat_L[i][j] = 0;
				}
				if(mat_adj[i][j]==1) {
					mat_L[i][j] = mat_val[i][j] ;
				}
				else {
					mat_L[i][j] = infini;
				}
			}
		}
		
		return mat_L;
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
				
				graphe_depart.test();
				
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