package classes.entites;

import classes.Inventaire;
import classes.objets.Arme;

/**
 * Represente le joueur dans le jeu
 */
public class Joueur extends Entite {

	/**
	 * Inventaire du joueur
	 */
	private Inventaire inventaire;

	/**
	 * Constructeur de la classe Joueur prenant en parametre les coordonnees de
	 * l'objet Joueur correspondant au milieu du labyrinthe
	 * 
	 * @param mx coordonnees du joueur en x
	 * @param my coordonnees du joueur en y
	 */
	public Joueur(int mx, int my) {
		super();
		if (mx < 0)
			this.setX(1);
		else
			this.setX(mx);
		if (my < 0)
			this.setY(1);
		else
			this.setY(my);
		this.setVie(10);
		this.setPermeabilite(false);

		this.setLastX(mx);
		this.setLastY(my);
		this.inventaire = new Inventaire();
	}

	/**
	 * Attaque une entite avec une arme
	 * 
	 * @param e entite a attaquer
	 * @param armeSelect arme selectionne
	 */
	public void attaquer(Entite e, Arme armeSelect) {
		e.setVie(e.getVie() - armeSelect.getPuissance());
	}

	/**
	 * Recupere l'inventaire du joueur
	 * 
	 * @return l'attribut inventaire
	 */
	public Inventaire getInventaire() {
		return this.inventaire;
	}

	@Override
	public void attaquer(Entite e, int pui) {}
}
