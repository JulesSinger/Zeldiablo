package tests.objets;

import static org.junit.Assert.*;

import org.junit.Test;

import classes.ZeldiabloJeu;
import classes.entites.Joueur;
import classes.objets.PotionForce;

public class Test_PotionForce {
	
	/**
	 * Test verifiant que la potion de force se trouve bien dans l'inventaire au moment du ramassage de celle-ci
	 */
	@Test
	public void test_possessionPotionForce() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		PotionForce pf = new PotionForce("potforce", 7, 7);
		jeu.getObjets().add(pf);
		
		//Methode testee
		jeu.ramasserObjet();
		
		//verification des donnees
		assertEquals("L'objet a la quatrieme position de l'inventaire devrait etre de la classe PotionForce", 
				true, jeu.getJoueur().getInventaire().getObjets().get(3) instanceof PotionForce);
	}
	
	/**
	 * Test verifiant que la potion de force applique bien ses effets sur l'arme equipee par le joueur
	 */
	@Test
	public void test_utilisationPotionForce() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		PotionForce pf = new PotionForce("potforce", 7, 7);
		Joueur joueur = jeu.getJoueur();
		joueur.getInventaire().getObjets().add(pf);
		
		//Methode testee
		jeu.utiliserObjet(4);
		
		//verification des donnees
		assertEquals("Le puissance de l'arme a augmente de 1 point", 2, jeu.chercherArmeSelectionnee().getPuissance());
	}
	
}
