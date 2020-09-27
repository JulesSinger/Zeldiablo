package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import classes.Inventaire;
import classes.ZeldiabloJeu;
import classes.objets.Objet;
import classes.objets.PotionSoin;

public class Test_Inventaire {
	
	@Test
	/**
	 * Test verifiant que les objets sont bien ajoutes dans l'inventaire
	 */
	public void test_remplissageInventaire() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Inventaire inventaire = jeu.getJoueur().getInventaire();
		Objet o1 = new PotionSoin("s1", 7, 7);
		Objet o2 = new PotionSoin("s2", 7, 7);
		jeu.getObjets().add(o1);
		jeu.getObjets().add(o2);

		//Methode testee
		jeu.ramasserObjet();
		jeu.ramasserObjet();

		//Verification des donnees
		assertEquals("La taille de la liste devrait etre de 5", 5, inventaire.getObjets().size());
	}
	
	@Test
	/**
	 * Test verifiant que la capacite de l'inventaire est limitee a 5
	 */
	public void test_CapaciteInventaire() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Inventaire inventaire = jeu.getJoueur().getInventaire();
		Objet o1 = new PotionSoin("s1", 7, 7);
		Objet o2 = new PotionSoin("s2", 7, 7);
		Objet o3 = new PotionSoin("s3", 7, 7);
		Objet o4 = new PotionSoin("s4", 7, 7);
		Objet o5 = new PotionSoin("s5", 7, 7);
		Objet o6 = new PotionSoin("s6", 7, 7);
		jeu.getObjets().add(o1);
		jeu.getObjets().add(o2);
		jeu.getObjets().add(o3);
		jeu.getObjets().add(o4);
		jeu.getObjets().add(o5);
		jeu.getObjets().add(o6);
		//Methode testee (ou partie)
		jeu.ramasserObjet();
		jeu.ramasserObjet();
		jeu.ramasserObjet();
		jeu.ramasserObjet();
		jeu.ramasserObjet();
		jeu.ramasserObjet();
		
		//verification des donees
		assertEquals("La taille de la liste devrait etre limite a 5", 5, inventaire.getObjets().size());
	}
	
	@Test
	/**
	 * Test verifiant que l'objet ramasse n'est plus ramassable (= a disparu)
	 */
	public void test_Disparition_objet() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		jeu.getJoueur().setX(7);
		jeu.getJoueur().setY(7);
		Objet o1 = new PotionSoin("s1",7, 7);
		jeu.getObjets().add(o1);
		jeu.ramasserObjet();
		
		//Verification des donnees
		assertEquals("L'objet devrait etre deja ramasse et donc non ramassable", false, jeu.getObjets().contains(o1));
	}

}
