package classes.cases;

/**
 * Represente un piege dans le labyrinthe. Ce piege peut faire des degats au
 * joueur
 */
public class CasePiege extends CaseActivable {

	/**
	 * Constructeur d'une case piegee
	 */
	public CasePiege() {
		this.etreActif(true);
	}

	@Override
	public boolean etreVide() {
		return true;
	}
}
