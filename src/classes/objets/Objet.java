package classes.objets;

/**
 * Represente un objet que peut posseder un joueur dans son inventaire ou place
 * dans le labyrinthe
 */
public abstract class Objet {

	/**
	 * Coordonnees de l'objet
	 */
	private int x, y;

	/**
	 * Si l'objet a ete utilise
	 */
	private boolean estUtilise;

	/**
	 * Nom de l'objet
	 */
	private String nom;

	/**
	 * Constructeur d'un objet
	 * 
	 * @param n nom de l'objet
	 */
	public Objet(String n) {
		this.nom = n;
		this.x = -1;
		this.y = -1;
		this.estUtilise = false;
	}

	/**
	 * Constructeur avec un objet non ramasser et non utilise
	 * 
	 * @param n nom de l'objet
	 * @param x abscisse
	 * @param y ordonnee
	 */
	public Objet(String n, int x, int y) {
		this.x = x;
		this.y = y;
		this.nom = n;
		this.estUtilise = false;
	}

	/**
	 * Recupere l'abcisse de l'objet
	 * 
	 * @return l'attribut x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Change l'abcisse de l'objet
	 * 
	 * @param x coordonnee a change
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Recupere l'ordonnee de l'objet
	 * 
	 * @return l'attribut y
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Change l'ordonnee de l'objet
	 * 
	 * @param y coordonnee a change
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Recupere si l'objet est utilise
	 * 
	 * @return l'attribut estUtilise
	 */
	public boolean etreUtilise() {
		return this.estUtilise;
	}

	/**
	 * Change l'etat de l'utilisation de l'objet
	 * 
	 * @param b true si l'objet est utilise
	 */
	public void setEstUtilise(boolean b) {
		this.estUtilise = b;
	}

	/**
	 * Recupere le nom de l'objet
	 * 
	 * @return l'attribut nom
	 */
	public String getNom() {
		return this.nom;
	}
}
