package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import classes.*;
import classes.cases.Case;
import classes.cases.CaseMur;

/**
 * tests qui ont pour but de verifier le fonctionnement de la classe Labyrinthe
 */
public class Test_Labyrinthe {

	@Test
	/**
	 * Test verifiant les dimensions du labyrinthe
	 */
	public void test_dimensionsValide() {
		// preparation des donnees
		Labyrinthe l = new Labyrinthe(new ZeldiabloJeu());
		// verification des donnees
		assertEquals("La largeur devrait etre 15", 15, l.getLargeur());
		assertEquals("La hauteur devrait etre 15", 15, l.getHauteur());

	}

	@Test
	/**
	 * Test verifiant que le labyrinthe est rempli d'objet de type Case
	 */
	public void test_initialisationCasesLabyrinthe() {
		// preparation des donnees
		Labyrinthe l = new Labyrinthe(new ZeldiabloJeu());
		boolean valide = true;

		// donnees testees
		// pour chaque ligne
		for (int i = 0; i < l.getLargeur(); i++) {
			// pour chaque colonne
			for (int j = 0; j < l.getHauteur(); j++) {
				// si l'objet n'est pas une case
				if (!(l.getCase(i, j) instanceof Case)) {
					// le test echoue
					valide = false;
				}
			}
		}

		// verification des donnees
		assertEquals("le booleen devrait etre vrai", true, valide);
	}

	@Test
	/**
	 * Test verifiant que la case du milieu est une case vide
	 */
	public void test_caseMilieuVide() {
		// preparation des donnees
		Labyrinthe l = new Labyrinthe(new ZeldiabloJeu());
		// verification des donnees
		assertEquals("La case du milieu devrait etre de type CaseVide", true,
				l.getCase(l.getXMilieu(), l.getYMilieu()).etreVide());

	}

	@Test
	/**
	 * Test verifiant si les contours du labyrinthe sont des murs, pour assurer que
	 * le joueur ne sorte pas du plateau
	 */
	public void test_contourLabyrinthe() {
		// preparation des donnees
		Labyrinthe l = new Labyrinthe(new ZeldiabloJeu());
		boolean valide = true;

		// donnees testees
		// pour chaque ligne
		for (int i = 0; i < l.getLargeur(); i++) {
			// pour chaque colonne
			for (int j = 0; j < l.getHauteur(); j++) {
				// si les coordonnees correspondent a une bordure
				if (i == l.getHauteur() || i == 0 || j == 0 || j == l.getLargeur()) {
					// si cette case n'est pas un mur
					if (!(l.getCase(i, j) instanceof CaseMur)) {
						// alors le test est faux
						valide = false;
					}
				}
			}
		}
		// verification des donnees
		assertEquals("le booleen devrait etre vrai", true, valide);
	}

}
