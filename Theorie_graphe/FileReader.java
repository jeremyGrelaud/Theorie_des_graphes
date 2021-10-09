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

		while (scanner.hasNextLine()) { // continue de lire tant qu'il y a des lignes
			String content = scanner.nextLine(); // enleve les espaces
			String[] values = content.split(" "); // envoie chaque mot dans un tableau de chaînes de caracs

			// On utilise un switch car les 2 premières lignes du fichier doivent être traitée spécifiquement
			switch (line) {
			case 0: // Ligne 1 = le nbr de sommets du grahe
				NbSommets = Integer.valueOf(content);
				graphe.setNbr_sommets(NbSommets);
				break;
			case 1: //le nbr d arcs
				NbArcs = Integer.valueOf(content);
				graphe.setNbArcs(NbArcs);
				break;
			default: // Les lignes suivantes sont tous les arcs de la forme 1 2 15 : sommet_depart sommet_arrivee valeur de l'ar

				// Si le sommet de depart n'existe pas deja dans le graphe
				if (!graphe.containsSommets(values[0])) {
					Sommet sommet = new Sommet(values[0]);
					Sommet sommet_arrivé = new Sommet(values[1]);
					sommet.addArc(values[2], sommet_arrivé);
					graphe.getSommets().add(sommet);
				}
				else { //s'il existe déjà on ajoute l'arc seulement on ne créer pas le sommet
					Sommet sommet_arrivé = new Sommet(values[1]);
					graphe.getSommetByNom(values[0]).addArc(values[2], sommet_arrivé);
				}
				
				// On initialise l'etat d'arrivee s'il n'existe pas déjà dans le graphe		
				if (!graphe.containsSommets(values[1])) {
					Sommet sommet = new Sommet(values[1]);
					graphe.getSommets().add(sommet);
				}
				break;
			}

			line++;
		}
		
		scanner.close();

		return graphe;
	}

	/**
	 * Permet de charger l'etat dans l'automate
	 * @param contient un string avec le nbr d'etats et leurs noms
	 * @param automate de type Automate dans lequel on va ajouter les nouveaux etats
	 * @param types d'états : ENTRY, COMMON, EXIT
	 */
	/*
	private static void loadEtats(String content, Automate automaton, TypeEtat type) {
		String[] values = content.split(" ");
		int numStates = Integer.valueOf(values[0]); // dans values[0] il y a le nbr d'etats
		// dans les values[i] i>0 les noms des etats

		for (int i = 1; i <= numStates; i++) {
			if (!automaton.containsEtats(values[i])) { //s'il n'existe pas encore
				Etat state = new Etat(values[i]);
				state.addType(type);
				automaton.getEtats().add(state);
			}
			else {
				automaton.getEtatByNom(values[i]).addType(type);
			}
		}
	}*/
}
