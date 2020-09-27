package classes.objets;

import java.util.ArrayList;

import classes.entites.Entite;
import classes.entites.Joueur;

/**
 * Potion de force qui permet d'augmenter la puissanec des armes
 */
public class PotionForce extends Objet implements Potion {

	/**
	 * Constructeur d'un potion de soin
	 * 
	 * @param n nom de l'objet
	 * @param x abscisse
	 * @param y ordonnee
	 */
	public PotionForce(String n, int x, int y) {
		super(n, x, y);
	}

	@Override
	public void utiliser(Entite e) {
		Joueur joueur = (Joueur) e;
		ArrayList<Objet> objets = joueur.getInventaire().getObjets();
		
		// Augmente la puissance des armes
		for (Objet o : objets) {
			if (o instanceof Arme) {
				Arme arme = (Arme) o;
				arme.setPuissance(arme.getPuissance() * 2);
			}
		}
	}

}
