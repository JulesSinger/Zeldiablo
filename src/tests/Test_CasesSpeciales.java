package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import classes.*;
import classes.cases.CasePortail;
import classes.entites.Joueur;
import classes.objets.Amulette;

public class Test_CasesSpeciales {
	
	/**
	 * Test verifiant qu'un piege s'active bien lorsqu'un joueur marche dessus (le joueur perd de la vie)
	 */
	@Test
	public void test_activationpiege() {
		// preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		
		// test des methodes
		jeu.getLabyrinthe().ajouterPiege(6, 7);
		jeu.getJoueur().seDeplacer(-1, 0);
		jeu.perdreViePiege();
		
		// verification des donnees
		assertEquals("Le joueur devrait avoir 10 - 1 = 9 point de vie", 9, jeu.getJoueur().getVie());
	}
	
	/**
	 * Test verifiant qu'un piege se reactive bien apres qu'un joueur ai marche dessus
	 */
	@Test
	public void test_reactivationpiege() {
		// preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		
		// test des methodes
		jeu.getLabyrinthe().ajouterPiege(6, 7);
		jeu.getJoueur().seDeplacer(-1, 0);
		jeu.perdreViePiege();
		jeu.getJoueur().seDeplacer(1, 0);
		jeu.activerLesPieges();
		jeu.getJoueur().seDeplacer(-1, 0);
		jeu.perdreViePiege();
		
		// verification des donnees
		assertEquals("Le joueur devrait avoir subit les degats du piege a 2 reprises et se retrouver a 8pv", 8, jeu.getJoueur().getVie());
	}
	
	
	/**
	 * Test verifiant si le portail est bien desactive par defaut
	 */
	@Test
	public void test_portailDesactive() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		
		// test des methodes
		CasePortail port = (CasePortail) jeu.getLabyrinthe().getPortail();
		
		//verification des donnees
		assertEquals("Le portail devrait etre desactive", false, port.getEtreActif());
	}
	
	/**
	 * Test verifiant si le joueur gagne lorsqu'il a recupere l'amulette et qu'il est sur le portail
	 */
	@Test
	public void test_finAvecAmulette() {
		//preparation des donnees
		ZeldiabloJeu jeu = new ZeldiabloJeu();
		Joueur j = (Joueur) jeu.getJoueur();
		Amulette am = new Amulette("am", j.getX(), j.getY()+1);
		jeu.getObjets().add(am);
		
		// test des methodes
		j.getInventaire().ajouterObjet(am);
		boolean vic = jeu.etreFini();
		
		//verification des donnees
		assertEquals("Le jeu devrait etre fini", true, vic);
	}
	
}