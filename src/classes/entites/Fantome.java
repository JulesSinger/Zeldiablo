package classes.entites;

/**
 * Represente un fantome dans le jeu. Le fantome est un monstre specifique.
 */
public class Fantome extends Monstre {

	/**
	 * Constructeur de la classe Fantome
	 * 
	 * @param mx coordonnees du monstre en x
	 * @param my coordonnees du monstre en y
	 * @param pui puissance du monstre
	 */
	public Fantome(int mx, int my, int pui) {
		super(mx, my, pui);
		this.setVie(1);
	}

}
