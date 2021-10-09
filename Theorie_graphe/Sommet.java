package Theorie_graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sommet{
	
	
	private String nom; //le nom du sommet allant de 0 à nbr_sommet-1

	private Map<String, List<Sommet>> arcs; //pour un numéro = key on aura le ou les sommets reliés
	//le numéro représentera la valeur de l'arc pour les graphes valués et sera égal à 0 sinon

	
	public Sommet(String nom) {
		this.nom=nom;
		arcs = new HashMap<String, List<Sommet>>();
	}
	
	public Sommet(Sommet sommet) { //construct de copie
		this.nom = sommet.getNom();
			
		HashMap<String, List<Sommet>> nouveaux_arcs = new HashMap<String, List<Sommet>>();
		
		
		for(String clef : sommet.getArc().keySet()) {
			List<Sommet> new_liste = new ArrayList<Sommet>();
			for(int i=0; i<sommet.getArc().get(clef).size();i++) {
				Sommet new_sommet = sommet.getArc().get(clef).get(i);
				new_liste.add(new_sommet);
			}
			nouveaux_arcs.put(clef, new_liste);
		}
		
		this.arcs = nouveaux_arcs;
	}
	

	public void addArc(String transitionWord, Sommet next) {
		
		if(!this.arcs.containsKey(transitionWord)){
			List<Sommet> new_liste = new ArrayList<Sommet>();
			new_liste.add(next);
			this.arcs.put(transitionWord, new_liste);
		}
		else {
			this.arcs.get(transitionWord).add(next);
		}
		
		
	} 
	public Map<String, List<Sommet>> getArc() {
		return arcs;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String id) {
		this.nom = id;
	}

	public void setArc(Map<String, List<Sommet>> liens) {
		this.arcs = liens;
	}

	@Override
	public String toString() {
		return this.getNom();
	}
	
	protected Sommet copieSommet() {
		
		Sommet new_sommet = new Sommet(this);
		return new_sommet;
		
	}
	
/*
	public void mergeWith(Etat etat_a_fusionner) {
		
		if(this == etat_a_fusionner) {
			return;
		}
		
		Map<String, List<Etat>> dico = mergeMaps(this.transition, etat_a_fusionner.getTransi());
		this.setTransi(dico);
		
		if(etat_a_fusionner.est_sortie() && !this.est_sortie()) {
			this.types.add(TypeEtat.EXIT);
		}
		
		String ancien_nom = this.nom;
		
		this.nom = String.join(".", this.nom, etat_a_fusionner.getNom());
		
	}
*/
	
/*	//fonction pour fusionner 2 map
	protected static Map<String, List<Etat>> mergeMaps(Map<String, List<Etat>> map1, Map<String, List<Etat>> map2){
		
		Map<String, List<Etat>> new_map = new HashMap<String, List<Etat>>();
		
		if(!map1.isEmpty() && !map2.isEmpty()) {
			
			new_map.putAll(map1);
			
			for(String clef : map2.keySet()) { //pour toutes les clefs de map2
				
					if(!new_map.containsKey(clef)) {
						new_map.put(clef, map2.get(clef));
					}
					
					else {
						List<Etat> Etats_new_map = new_map.get(clef);
						List<Etat> Etats_map2 = map2.get(clef);
						
						List<Etat> fusion = mergeLists(Etats_new_map, Etats_map2);
						new_map.remove(clef);
						new_map.put(clef, fusion);
					}
				
			}
			
			return new_map;
		}
		
		if(map1.isEmpty() && !map2.isEmpty()) {
			return map2;
		}
		else {
			return map1;
		}
		//si la condition est vrai on retourne map2 sinon map1
	}
*/

	///on n'aura pas besoin de la fusion je crois
	protected static List<Sommet> mergeLists(List<Sommet> l1, List<Sommet> l2) {
		
		List<Sommet> les_sommets = new ArrayList<Sommet>();
		
		les_sommets.addAll(l1);
		
		for(Sommet sommet : l2) { //pour tous les sommets de la deuxieme liste
			if(!les_sommets.contains(sommet)) { //s'ils ne sont pas deja contenu dans la nouvelle liste
				les_sommets.add(sommet); //on les ajoute
			}
		}
		
		return les_sommets; //on retourne la liste equivalente à la fusion des 2 listes
	}
	
	@Override
	public boolean equals(Object o) { //si 2 sommets ont le meme nom/chiffre alors on les considerent egaux
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		
		Sommet sommet = (Sommet) o;
		return this.getNom().equals(sommet.getNom());
		
		
	}
	

}



