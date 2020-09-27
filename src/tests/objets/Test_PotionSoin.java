package tests.objets;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.ZeldiabloJeu;
import classes.entites.Joueur;
import classes.objets.PotionSoin;

public class Test_PotionSoin {

	@Test
	/**
	 * Verifie si la potion de PotionSoin est bien ramassee
	 */
	public void test_ramasserPotion() {
		// Preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Joueur joueur = jeu.getJoueur();
		joueur.setX(0);
		joueur.setY(0);

		PotionSoin potion = new PotionSoin("Potion de soin", 0, 0);
		jeu.getObjets().add(potion);

		// Methode testee
		jeu.ramasserObjet();

		// Verification
		assertEquals("Le joueur devrait posseder une potion supplementaire quand il en ramasse une", 4,
				joueur.getInventaire().getObjets().size());
		assertEquals("La potion devrait etre ramassee", false, jeu.getObjets().contains(potion));
	}

	@Test
	/**
	 * Teste si la potion donne bien de la vie et qu'elle est utilisee
	 */
	public void test_potionDonneVie() {
		// Preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Joueur joueur = jeu.getJoueur();
		joueur.setVie(1);

		// Methode testee
		jeu.utiliserObjet(3);

		// Verification
		assertEquals("La potion devrait soigner de 3 points de vie le joueur", 4, joueur.getVie());
		assertEquals("La potion devrait etre utilisee", 2, joueur.getInventaire().getObjets().size());
	}

	@Test
	/**
	 * Teste si le joueur n'est pas soigne s'il ne possede pas de potion
	 */
	public void test_soignePasSiPasDePotionDansInventaire() {
		// Preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Joueur joueur = jeu.getJoueur();
		joueur.setVie(1);
		joueur.getInventaire().supprimerObjet(0);

		// Methode testee
		jeu.utiliserObjet(1);

		// Verification
		assertEquals("Le joueur ne devrait pas gagner des points de vie s'il ne possede pas de potion", 1,
				joueur.getVie());
	}

	@Test
	/**
	 * Teste si le joueur ne depasse pas ses points de vie maximaux quand il est
	 * soigne par une potion
	 */
	public void test_soigneMaxPointsDeVie() {
		// Preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Joueur joueur = jeu.getJoueur();
		joueur.setVie(9);

		// Methode testee
		jeu.utiliserObjet(3);

		// Verification
		assertEquals("Le joueur ne devrait pas exceder ses points de vie maximaux quand il est soigne par une potion", 10,
				joueur.getVie());
	}
}
