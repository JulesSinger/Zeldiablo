package classes.cases;

/**
 * Represente le portail de sortie du labyrinthe. Ce portail ne peut etre
 * utilise que si le joueur possede l'amulette.
 */
public class CasePortail extends CaseActivable {
	/**
	 * Constructeur d'un portail
	 */
	public CasePortail() {
		this.etreActif(false);
	}

	@Override
	public boolean etreVide() {
		return true;
	}
}
