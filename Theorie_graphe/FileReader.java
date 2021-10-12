package Theorie_graphe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	//methode de classe
	public static Graphe createGrapheObject(String filePath) throws FileNotFoundException {

		File file = new File(filePath); // chemin vers le fichier txt
		Scanner scanner = new Scanner(file); 

		Graphe graphe = new Graphe(); //creation de l'automate à partir du constructeur par défaut
 
		// On initialise les composantes à 0 ou null
		int line = 0;
		int NbSommets = 0;
		int NbArcs = 0;
		float[][] matrice_d_adjacence = null;
		float[][] matrice_de_valeurs = null;

		while (scanner.hasNextLine()) { // continue de lire tant qu'il y a des lignes
			String content = scanner.nextLine(); // enleve les espaces
			String[] values = content.split(" "); // envoie chaque mot dans un tableau de chaînes de caracs

			// On utilise un switch car les 2 premières lignes du fichier doivent être traitée spécifiquement
			switch (line) {
			case 0: // Ligne 1 = le nbr de sommets du grahe
				NbSommets = Integer.valueOf(content);
				graphe.setNbr_sommets(NbSommets);
				matrice_d_adjacence = new float[NbSommets][NbSommets] ;
				matrice_de_valeurs = new float[NbSommets][NbSommets] ;
				
				break;
			case 1: //le nbr d arcs
				NbArcs = Integer.valueOf(content);
				graphe.setNbArcs(NbArcs);
				break;
			default: // Les lignes suivantes sont tous les arcs de la forme 1 2 15 : sommet_depart sommet_arrivee valeur de l'arc
				
				
				matrice_d_adjacence[Integer.valueOf(values[0])][Integer.valueOf(values[1])] = 1;
				matrice_de_valeurs[Integer.valueOf(values[0])][Integer.valueOf(values[1])] = Integer.valueOf(values[2]);
				
				break;
			}

			line++;
		}
		
		graphe.setMatrice_adjacence(matrice_d_adjacence);
		graphe.setMatrice_valeurs(matrice_de_valeurs);
		
		scanner.close();

		return graphe;
	}
}
