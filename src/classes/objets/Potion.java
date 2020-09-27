package classes.objets;

import classes.entites.Entite;

/**
 * Represente une potion a consommer
 */
public interface Potion {
	/**
	 * Action pour utiliser la potion
	 * 
	 * @param e entite qui utilise la potion
	 */
	public void utiliser(Entite e);
}
