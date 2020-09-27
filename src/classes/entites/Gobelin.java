package classes.entites;

/**
 * Represente un gobelin dans le jeu. Le gobelin est un monstre specifique.
 */

public class Gobelin extends Monstre{
	
	/**
	 * Constructeur parametre
	 * 
	 * @param mx  coordonnees du monstre en x
	 * @param my  coordonnees du monstre en y
	 * @param pui puissance du monstre
	 */
	public Gobelin(int mx, int my, int pui) {
		super(mx, my, pui);
		this.setVie(3);
	}

}
