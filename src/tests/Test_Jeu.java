package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import classes.*;
import classes.entites.Entite;
import classes.entites.Fantome;
import classes.entites.Joueur;
import classes.entites.Monstre;

public class Test_Jeu {

	/**
	 * Verifie l'initialisation des monstres
	 */
	@Test
	public void test_initialisationDesMonstres() {

		// Preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		boolean unSurMur=false;
		boolean unSurJoueur=false;

		ArrayList<Entite> entites = jeu.getEntites();
		Joueur j = (Joueur) jeu.getJoueur();
		Labyrinthe l = jeu.getLabyrinthe();

		int nbMonstres = 0;

		// Methodes a tester
		for (int i=0; i<entites.size(); i++) {
			if (entites.get(i) instanceof Monstre) {

				nbMonstres++;
				if (!(entites.get(i) instanceof Fantome)) {
					// test si un monstre est sur le joueur
					if (entites.get(i).getX() == j.getX() && entites.get(i).getY() == j.getY()) unSurJoueur=true;

					// test si un monstre est sur un mur
					if (! l.getCase(entites.get(i).getX(), entites.get(i).getY()).etreVide()) unSurMur=false;
				}
			}
		}

		// Verification
		assertEquals("Il doit y avoir 8 monstres au depart du jeu", 8, nbMonstres);
		assertEquals("Les monstre ne doivent pas etre dans un mur", false, unSurMur);
		assertEquals("Les monstre ne doivent pas etre sur le joueur", false, unSurJoueur);
	}

	/**
	 * Verifie la Fin du jeu en cas de defaite du joueur
	 */
	@Test
	public void test_FinDuJeuDefaite() {

		// Preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		ArrayList<Entite> entites = jeu.getEntites();
		Joueur j = (Joueur) jeu.getJoueur();

		// Methodes a tester
		for (int i=1; i<entites.size()*2; i++) {
			
			if (entites.get(i/2) instanceof Monstre) {
				entites.get(i/2).attaquer(j, ((Monstre) entites.get(i/2)).getPuissance());
			}
		}

		// Verification
		assertEquals("Le jeu devrait etre fini", true, jeu.etreFini());
		assertEquals("Le joueur devrait etre vivant", true, j.etreMort());
	}
}