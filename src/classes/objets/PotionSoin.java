package classes.objets;

import classes.entites.Entite;

/**
 * Represente une potion de soin qui peut soigner le joueur
 */
public class PotionSoin extends Objet implements Potion {

	/**
	 * Constructeur d'un potion de soin
	 * 
	 * @param n nom de l'objet
	 * @param x abscisse
	 * @param y ordonnee
	 */
	public PotionSoin(String n, int x, int y) {
		super(n, x, y);
	}

	@Override
	public void utiliser(Entite e) {
		e.setVie(e.getVie() + 3);

		// la vie ne doit pas depasser 10
		if (e.getVie() > 10)
			e.setVie(10);
	}
}
