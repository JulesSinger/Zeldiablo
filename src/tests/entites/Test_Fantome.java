package tests.entites;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import classes.entites.Entite;
import classes.entites.Fantome;
import classes.entites.Joueur;
import classes.entites.Monstre;
import classes.objets.Arme;
import classes.ZeldiabloJeu;

public class Test_Fantome {
	@Test
	/**
	 * Teste la presence de fantomes dans les entites / dans le jeu
	 */
	public void test_presenceFantomes() {
		// Preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		ArrayList<Entite> entites = jeu.getEntites();
		
		// Methode testee (ou partie)
		boolean presence = false;
		for (Entite e : entites) {
			if (e instanceof Fantome) {
				presence = true;
				break;
			}
		}
		
		// Verifications
		assertEquals("Il doit y avoir au moins un fantome dans les entites", true, presence);
	}
	
	@Test
	/**
	 * Teste le joueur qui attaque un fantome
	 */
	public void test_joueurAttaqueFantome() {
		// Preparation des donnees
		Joueur j = new Joueur(7, 7);
		Fantome f = new Fantome(6, 7, 1);
		
		int vieJoueur = j.getVie();
		int vieFantome = f.getVie();
		
		// Methode testee
		j.attaquer(f,(Arme)j.getInventaire().getObjets().get(0));
		
		// Verifications
		assertEquals("Le joueur ne doit pas perdre de vie quand il attaque un fantome", vieJoueur, j.getVie());
		assertNotEquals("Le fantome doit perdre de la vie quand le joueur l'attaque", vieFantome, f.getVie());
	}
	
	@Test
	/**
	 * Teste le fantome qui attaque un joueur
	 */
	public void test_fantomeAttaqueJoueur() {
		// Preparation des donnees
		Joueur j = new Joueur(0, 0);
		Fantome f = new Fantome(0, 0, 1);
		
		int vieJoueur = j.getVie();
		int vieFantome = f.getVie();
		
		// Methode testee
		f.attaquer(j, ((Monstre)f).getPuissance());
		
		// Verifications
		assertEquals("Le fantome ne doit pas perdre de vie quand il attaque un joueur", vieFantome, f.getVie());
		assertNotEquals("Le joueur doit perdre de la vie quand le fantome l'attaque", vieJoueur, j.getVie());
	}
}
