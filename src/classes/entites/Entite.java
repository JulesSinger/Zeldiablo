package classes.entites;

/**
 * Represente une entite dans le jeu
 */
public abstract class Entite {

	/**
	 * Coordonnees de l'entite et ancienne coordonnees
	 */
	private int x, y;

	/**
	 * Si l'entite peut etre traversee
	 */
	private boolean estPermeable;

	/**
	 * Anciennes coordonnees
	 */
	private int lastX, lastY;

	/**
	 * Points de vie de l'entite
	 */
	private int pointsDeVie;

	/**
	 * Renvoit les coordonnees en x de l'entite
	 * 
	 * @return l'attribut x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Renvoit les coordonnees en y de l'entite
	 * 
	 * @return l'attribut y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Definit les coordonnees en x de l'entite
	 * 
	 * @param nx coordonnee en x
	 */
	public void setX(int nx) {
		this.x = nx;
	}

	/**
	 * Definit les coordonnees en y de l'entite
	 * 
	 * @param ny coordonnee en y
	 */
	public void setY(int ny) {
		this.y = ny;
	}

	/**
	 * Renvoit les anciennes coordonnees en x de l'entite
	 * 
	 * @return l'attribut lastX
	 */
	public int getLastX() {
		return this.lastX;
	}

	/**
	 * Definit les coordonnees en x de l'entite
	 * 
	 * @param x coordonnee en lastX
	 */
	public void setLastX(int x) {
		this.lastX = x;

	}

	/**
	 * Renvoit les anciennes coordonnees en y de l'entite
	 * 
	 * @return l'attribut lastY
	 */
	public int getLastY() {
		return this.lastY;
	}
	
	/**
	 * Definit les coordonnees en y de l'entite
	 * 
	 * @param y coordonnee en lastY
	 */
	public void setLastY(int y) {
		this.lastY = y;
	}

	/**
	 * Renvoit les points de vie de l'entite
	 * 
	 * @return points de vie
	 */
	public int getVie() {
		return this.pointsDeVie;
	}

	/**
	 * Definit les points de vie de l'entite
	 * 
	 * @param v points de vie
	 */
	public void setVie(int v) {
		this.pointsDeVie = v;
		
		if(this.pointsDeVie <= 0)
			this.estPermeable = true;
	}

	/**
	 * Verifie si l'entite est morte
	 * 
	 * @return si l'entite est morte ou non
	 */
	public boolean etreMort() {
		return this.getVie() <= 0;
	}

	/**
	 * Modifie les coordonnees de l'entite en tenant des comptes des parametres
	 * 
	 * @param px deplacement en x
	 * @param py deplacement en y
	 */
	public void seDeplacer(int px, int py) {
		this.x += px;
		this.y += py;
	}

	/**
	 * Verifie si l'entite est permeable ou non
	 * 
	 * @return si l'entite est permeable ou non
	 */
	public boolean etrePermeable() {
		return this.estPermeable;
	}

	/**
	 * Definit la permeabilite de l'entite
	 * 
	 * @param b permeabilite
	 */
	public void setPermeabilite(boolean b) {
		this.estPermeable = b;
	}

	/**
	 * Attaque une entite
	 * 
	 * @param e entite
	 * @param pui entier
	 */
	public abstract void attaquer(Entite e, int pui);
}
