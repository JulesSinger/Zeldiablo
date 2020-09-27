package classes.entites;

/**
 * Represente un monstre dans le jeu. Un monstre est hostile pour le joueur.
 */
public class Monstre extends Entite {
	
	/**
	 * puissance d'attaque du monstre
	 */
	private int puissance; 
	
	/**
	 * Constructeur de la classe Monstre
	 * 
	 * @param mx coordonnees du monstre en x
	 * @param my coordonnees du monstre en y
	 * @param pui puissance du monstre
	 */
	public Monstre(int mx, int my, int pui) {
		super();
		if (mx < 0)
			this.setX(1);
		else
			this.setX(mx);
		if (my < 0)
			this.setY(1);
		else
			this.setY(my);
		this.setVie(2);
		this.setPermeabilite(false);
		this.puissance = pui;
	}

	@Override
	public void attaquer(Entite e, int pui) {
		if (this.getVie() > 0)
			e.setVie(e.getVie() - pui);
	}
	
	/**
	 * permet de connaitre la puissance du monstre
	 * @return l'attribut puissance
	 */
	public int getPuissance() {
		return this.puissance;
	}
}
