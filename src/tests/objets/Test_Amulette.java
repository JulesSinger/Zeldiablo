package tests.objets;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import classes.ZeldiabloJeu;
import classes.objets.Amulette;
import classes.objets.Objet;

/**
 * tests qui ont pour but de verifier le fonctionnement de l'amulette
 */
public class Test_Amulette {
	
	
	@Test
	/**
	 * Test verifiant le constructeur de l'amulette
	 */
	public void test_constructeur_amulette() {
		// initialisation
		Objet o = new Amulette("Amulette", 4,5);
		
		// verifications
		assertEquals("l'amulette doit etre en x=4", 4,o.getX());
		assertEquals("l'amulette doit etre en y=5", 5,o.getY());
		assertEquals("l'amulette doit avoir pour nom Amulette", "Amulette",o.getNom());
		assertEquals("l'amulette ne doit pas avoir ete utilise", false,o.etreUtilise());
	}
	
	@Test
	/**
	 * Test verifiant que le joueur peut ramasser une amulette
	 */
	public void testRamasserAmulette() {
		// initialisation
		ZeldiabloJeu j = new ZeldiabloJeu();
		ArrayList<Objet> objetsInventaire = j.getJoueur().getInventaire().getObjets();
		
		Objet o = new Amulette("Amulette", 7,7);
		j.getObjets().add(o);
		// appels des methodes
		j.ramasserObjet();

		// verifications
		assertEquals("Le joueur doit avoir un objet dans son inventaire", 4, objetsInventaire.size());
		
	}
	
	@Test
	/**
	 * Test verifiant que le joueur a bien une amulette
	 */
	public void testAvoirAmulette() {
		// initialisation
		ZeldiabloJeu j = new ZeldiabloJeu();
		
		Objet o = new Amulette("Amulette", 7,7);
		j.getObjets().add(o);
		// appels des methodes
		j.ramasserObjet();

		// verifications
		assertEquals("Le joueur doit avoir un objet dans son inventaire", true, j.getJoueur().getInventaire().getObjets().get(3) instanceof Amulette);
		
	}
}
