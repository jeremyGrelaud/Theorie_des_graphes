package Theorie_graphe;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.Scanner;

import Theorie_graphe.FileReader;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {

		
		//portion de code permettant de generer les traces en tapant le numero de l automate
		/*
		try { // Ajout d'un try  catch

            PrintStream console = System.out; // Ajoute ça 

            PrintStream fileOut = new PrintStream("src/Automate_1/traces/A8-trace44.txt"); // Et ça

            System.setOut(fileOut); // Et ça

            //CODE 
            Trace();

            System.setOut(console); // Et ça à la fin

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }*/
		
		//lance l'interface du programme
		Execution();
		
		
	}
	
	private static void Execution() {
		
		Scanner sc = new Scanner(System.in);
		String mot = "";
		
		do {
			Graphe graphe = choisirGraphe();
			graphe.programme();
		
			
			System.out.println("Si vous voulez quitter tapez EXIT");
			System.out.println("Pour continuer tapez autre chose");
			mot = sc.nextLine();
			
			
		}while(!mot.equals("EXIT"));
		
		System.out.println("Fin du programme");
		
		sc.close();
		
	}
	
	private static Graphe choisirGraphe() {
		
		Scanner sc = new Scanner(System.in);
		int mot;
		Graphe graphe = null;
		
			System.out.println("Veuillez ecrire le numero du graphe :");
			System.out.println("(un chiffre de 1 a 44) ");
			mot = sc.nextInt();
			
			
			while(mot<1 || mot>44) {
				System.out.println("Attention :");
				System.out.println("(un chiffre de 1 a 44 ");
				mot = sc.nextInt();
			}
			
			
			String path = getChemin("G8-"+mot);
			try {
				graphe = FileReader.createGrapheObject(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//sc.close();
		return graphe;
	}
	
	//renvoi le path de l'automate choisi en entrant son nom
	private static String getChemin(String graphe_choisi) {
	
		/*
		File root = null;
		
		try {
			root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String chemin = root.toString();
		chemin = chemin.replaceAll("bin", "");*/
		
		//D:/Efrei/L2/java/workspace/Theorie_des_graphes_L3
		String chemin = "D:/Efrei/L2/java/workspace/Theorie_des_graphes_V2/";
		
		String path = chemin + "src/Theorie_graphe/files/" + graphe_choisi +".txt";
		
		return path;
	
	}
	
	
}