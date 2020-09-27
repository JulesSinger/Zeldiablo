package tests.entites;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import classes.*;
import classes.entites.Entite;
import classes.entites.Joueur;
import classes.entites.Monstre;
import classes.objets.*;

public class Test_Joueur {

	/**
	 * Verifie l'initialisation du joueur en verifiant ses coordonnees
	 */
	@Test
	public void test_initialiserJoueur() {
		// Preparation des donnees
		Joueur j = new Joueur(5, 15);

		// Methodes a tester
		int x = j.getX();
		int y = j.getY();
		
		// Verification
		assertEquals("les coordonnes en X du joueur ne sont pas corrects", 5, x);
		assertEquals("les coordonnes en Y du joueur ne sont pas corrects", 15, y);
	}
	
	/**
	 * Verifie l'initialisation du joueur en verifiant ses coordonnees avec des
	 * coordonnees negatifs
	 */
	@Test
	public void test_initialiserJoueur_valeur_negatives() {
		// Preparation des donnees
		Joueur j = new Joueur(-1, -20);

		// Methodes a tester
		int x = j.getX();
		int y = j.getY();

		// Verification
		assertEquals("les coordonnes en X du joueur ne sont pas corrects par defaut", 1, x);
		assertEquals("les coordonnes en Y du joueur ne sont pas corrects par defaut", 1, y);
	}

	@Test
	/**
	 * Test verifiant le bon deplacement du Joueur en haut a droite
	 */
	public void testDeplacerJoueurHautDroite() {
		// initialisation
		Joueur j = new Joueur(0, 0);
		int x = j.getX();
		int y = j.getY();

		// appels des methodes
		j.seDeplacer(0, -1);
		j.seDeplacer(1, 0);

		// verifications
		assertEquals("Le joueur doit avoir ete deplace de 1 a droite", x + 1, j.getX());
		assertEquals("Le joueur doit avoir ete deplace de 1 en haut", y - 1, j.getY());
	}

	@Test
	/**
	 * Test verifiant le bon deplacement du Joueur en bas a gauche
	 */
	public void testDeplacerJoueurBasGauche() {
		// initialisation
		Joueur j = new Joueur(0, 0);

		int x = j.getX();
		int y = j.getY();

		// appels des methodes
		j.seDeplacer(0, 1);
		j.seDeplacer(-1, 0);

		// verifications
		assertEquals("Le joueur doit avoir ete deplace de 1 a droite", x - 1, j.getX());
		assertEquals("Le joueur doit avoir ete deplace de 1 en haut", y + 1, j.getY());
	}

	@Test
	/**
	 * Test verifiant qu'un joueur ne peut pas se deplacer lorsqu'un mur le bloque
	 */
	public void test_collisionMur() {
		// preparation des donnees
		ZeldiabloJeu j = new ZeldiabloJeu();
		j.getLabyrinthe().ajouterMur(8, 7);
		// Supprime les montres pour eviter collision
		ArrayList<Entite> entites = j.getEntites();
		for (Entite e : entites) {
			if (!(e instanceof Joueur)) {
				e.setVie(0);
			}
		}

		// methode testee
		j.deplacerJoueur("right");

		// verifications des donnees
		assertEquals("x devrait etre 7", 7, j.getJoueur().getX());
		assertEquals("y devrait etre 7", 7, j.getJoueur().getY());
	}

	@Test
	/**
	 * Test verifiant qu'un joueur peut se deplacer lorsqu'il n'y a pas de mur pour
	 * le bloquer
	 */
	public void test_NonCollisionMur() {
		// preparation des donnees
		ZeldiabloJeu j = new ZeldiabloJeu();
		j.getLabyrinthe().ajouterMur(8, 6);

		// Supprime les montres pour eviter collision
		ArrayList<Entite> entites = j.getEntites();
		for (Entite e : entites) {
			if (!(e instanceof Joueur)) {
				e.setVie(0);
			}
		}

		// methode testee
		j.deplacerJoueur("left");

		// verifications des donnees
		assertEquals("x devrait etre 6", 6, j.getJoueur().getX());
		assertEquals("y devrait etre 7", 7, j.getJoueur().getY());
	}

	
	/**
	 * Teste si le joueur possede l'epee de base
	 */
	@Test
	public void test_possessionEpee() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		boolean b = false;
		if(jeu.getJoueur().getInventaire().getObjets().get(0) instanceof Epee) {
			b = true;
		}
		
		//verification des donnees
		assertEquals("L'epee devrait se trouver dans l'inventaire � la premiere place", true, b);
	}
	
	/**
	 * Teste l'attaque avec une epee
	 */
	@Test
	public void test_attaqueEpee() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Monstre m = new Monstre(6,7,1);
		jeu.getEntites().add(m);
		
		//Methode testee
		jeu.attaquerLesMonstres(jeu.chercherArmeSelectionnee());
		
		//verification des donnees
		assertEquals("Le monstre devrait avoir 1 pv", 1, m.getVie());
	}
	
	/**
	 * Teste si l'epee attaque plusieurs monstres
	 */
	@Test
	public void test_attaqueEpeeSurPlusieursMonstres() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Monstre m = new Monstre(6,7,1);
		Monstre m2 = new Monstre(7,6,1);
		jeu.getEntites().add(m);
		jeu.getEntites().add(m2);
		
		//Methode testee
		jeu.attaquerLesMonstres(jeu.chercherArmeSelectionnee());
		
		//verification des donnees
		assertEquals("Le monstre m devrait avoir 1 pv", 1, m.getVie());
		assertEquals("Le monstre m2 devrait avoir 1 pv", 1, m2.getVie());
	}
	
	/**
	 * Teste si le joueur possede bien l'arc
	 */
	@Test
	public void test_possessionArc() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		boolean b = false;
		if(jeu.getJoueur().getInventaire().getObjets().get(1) instanceof Arc) {
			b = true;
		}
		
		//verification des donnees
		assertEquals("L'arc devrait se trouver dans l'inventaire � la deuxieme place", true, b);
	}
	
	/**
	 * Teste l'attaque avec un arc
	 */
	@Test
	public void test_attaqueArc() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Joueur joueur = jeu.getJoueur();
		joueur.setX(7);
		joueur.setY(7);
		Monstre m = new Monstre(5,7,1);
		jeu.getEntites().add(m);
		((Arme) joueur.getInventaire().getObjets().get(0)).equiperArme(false);
		((Arme) joueur.getInventaire().getObjets().get(1)).equiperArme(true);
		
		//Methode testee
		jeu.attaquerLesMonstres(jeu.chercherArmeSelectionnee());
		
		//verification des donnees
		assertEquals("Le monstre devrait avoir 1 pv", 1, m.getVie());
	}
	
	/**
	 * Teste l'attaque avec un arc derriere un mur
	 */
	@Test
	public void test_attaqueArcDerriereMur() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Monstre m = new Monstre(5,7,1);
		jeu.getEntites().add(m);
		Joueur joueur = jeu.getJoueur();
		joueur.setX(7);
		joueur.setY(7);
		jeu.getLabyrinthe().ajouterMur(6, 7);
		((Arme) joueur.getInventaire().getObjets().get(0)).equiperArme(false);
		((Arme) joueur.getInventaire().getObjets().get(1)).equiperArme(true);
		
		//Methode testee
		jeu.attaquerLesMonstres(jeu.chercherArmeSelectionnee());
		
		//verification des donnees
		assertEquals("Le monstre ne devrait pas etre mort", false, m.etreMort());
	}
}
