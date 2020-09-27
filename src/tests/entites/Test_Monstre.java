package tests.entites;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.entites.Entite;
import classes.entites.Joueur;
import classes.entites.Monstre;

/**
 * tests qui ont pour but de verifier le fonctionnement de la classe Monstre
 */
public class Test_Monstre {
	
	@Test
	/**
	 * Test verifiant le bon deplacement du monstre
	 */
	public void testDeplacerJoueurHautDroite() {
		// initialisation
		Monstre m = new Monstre(1, 1, 1);
		int x = m.getX();
		int y = m.getY();

		// appels des methodes
		m.seDeplacer(0, -1);
		m.seDeplacer(1, 0);

		// verifications
		assertEquals("Le monstre doit avoir ete deplace de 1 a droite", x + 1, m.getX());
		assertEquals("Le monstre doit avoir ete deplace de 1 en haut", y - 1, m.getY());
	}
	
	@Test
	/**
	 * Test verifiant l'attaque des monstres (les monstres attaquent le joueur)
	 */
	public void test_attaqueMonstres() {
		// Preparation des donnees
		Entite joueur = new Joueur(0, 0);
		Entite monstre = new Monstre(0, 0, 1);
		
		int vieJoueur = joueur.getVie();
		int vieMonstre = monstre.getVie();
		
		// Methode testee
		monstre.attaquer(joueur, ((Monstre) monstre).getPuissance());
		
		// Verifications des donnees
		assertEquals("La vie du monstre ne devrait pas changer lorsqu'il attaque le joueur.", vieMonstre, monstre.getVie());
		assertEquals("La vie du joueur devrait baisser lorsque le monstre attaque le joueur.", true, vieJoueur > joueur.getVie());
	}
	
}