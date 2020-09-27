package classes.cases;

/**
 * Represente une simple case vide dans la labyrinthe
 */
public class CaseVide implements Case {

	@Override
	public boolean etreVide() {
		return true;
	}
}
