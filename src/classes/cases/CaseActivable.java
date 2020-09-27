package classes.cases;

/**
 * Represente une case qui peut etre activee ou non
 */
public abstract class CaseActivable implements Case {
	/**
	 * Si la case est active ou non
	 */
	private boolean estActif;
	
	@Override
	public abstract boolean etreVide();
	
	/**
	 * Active la case ou la desactive (pour les pieges)
	 * @param b vrai ou faux
	 */
	public void etreActif(boolean b) {
		this.estActif = b;
	}

	/**
	 * Verifie si la case est active ou non
	 * 
	 * @return si la case est active ou non
	 */
	public boolean getEtreActif() {
		return this.estActif;
	}
}
