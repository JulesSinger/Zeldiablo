package main;

import classes.*;
import moteurJeu.*;

public class ZeldiabloMain {
	public static void main(String[] args) throws InterruptedException {
	
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		ZeldiabloDessinJeu dessinJeu = new ZeldiabloDessinJeu(jeu);
		MoteurGraphique moteurGraph = new MoteurGraphique(jeu, dessinJeu);
		int largeurLab = jeu.getLabyrinthe().getLargeur();
		int hauteurLab = jeu.getLabyrinthe().getHauteur() + 1;
		moteurGraph.lancerJeu(ZeldiabloDessinJeu.TAILLE*largeurLab,ZeldiabloDessinJeu.TAILLE*hauteurLab);
	}
}
