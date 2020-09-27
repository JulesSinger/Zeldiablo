package tests.entites;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.entites.Entite;
import classes.entites.Joueur;
import classes.entites.Monstre;
import classes.entites.Gobelin;

/**
 * tests qui ont pour but de verifier le fonctionnement de la classe Monstre
 */
public class Test_Gobelins {
	
	@Test
	/**
	 * Test verifiant le bon deplacement du monstre
	 */
	public void testDeplacerJoueurHautDroite() {
		// initialisation
		Gobelin g = new Gobelin(1, 1, 2);
		int x = g.getX();
		int y = g.getY();

		// appels des methodes
		g.seDeplacer(0, -1);
		g.seDeplacer(1, 0);

		// verifications
		assertEquals("Le monstre doit avoir ete deplace de 1 a droite", x + 1, g.getX());
		assertEquals("Le monstre doit avoir ete deplace de 1 en haut", y - 1, g.getY());
	}
	
	@Test
	/**
	 * Test verifiant l'attaque des monstres (les monstres attaquent le joueur)
	 */
	public void test_attaqueMonstres() {
		// Preparation des donnees
		Entite joueur = new Joueur(0, 0);
		Gobelin gobelin = new Gobelin(0, 0, 2);
		
		int vieJoueur = joueur.getVie();
		int vieMonstre = gobelin.getVie();
		
		// Methode testee
		gobelin.attaquer(joueur, ((Monstre) gobelin).getPuissance());
		
		// Verifications des donnees
		assertEquals("La vie du monstre ne devrait pas changer lorsqu'il attaque le joueur.", vieMonstre, gobelin.getVie());
		assertEquals("La vie du joueur devrait baisser lorsque le monstre attaque le joueur.", true, vieJoueur > joueur.getVie());
	}
	
}