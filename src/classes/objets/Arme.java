package classes.objets;

/**
 * Classe representant les armes (ce sont des objets)
 */
public abstract class Arme extends Objet {
	
	/**
	 * Puissance de l'arme
	 */
	private int puissance;
	
	/**
	 * Si l'arme est equipee ou non
	 */
	private boolean estEquipee;
		
	/**
	 * Constructeur de la classe arme
	 * 
	 * @param n   correspondant au nom de l'objet
	 * @param pui correspondant a la puissance de l'arme
	 */
	public Arme(String n, int pui) {
		super(n);
		this.puissance = pui;
	}

	/**
	 * Recupere la puissance de l'arme
	 * 
	 * @return puissance de l'arme
	 */
	public int getPuissance() {
		return this.puissance;
	}
	
	/**
	 * Definit la puissance de l'arme
	 * 
	 * @param pui puissance de l'arme
	 */
	public void setPuissance(int pui) {
		this.puissance = pui;
	}

	/**
	 * Verifie si le joueur est equipe de l'arme
	 * 
	 * @return si le joueur est equipe de l'arme ou non
	 */
	public boolean etreEquipee() {
		return this.estEquipee;
	}

	/**
	 * Modifie le statut d'equipe ou non de l'arme
	 * 
	 * @param b equiper le joueur ou non
	 */
	public void equiperArme(boolean b) {
		this.estEquipee = b;
	}
}
