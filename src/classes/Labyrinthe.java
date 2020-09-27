package classes;

import java.util.ArrayList;

import classes.cases.*;
import classes.entites.Entite;
import classes.entites.Joueur;

/**
 * Represente le labyrinthe dans lequel se place tous les items du jeu
 */
public class Labyrinthe {

	/**
	 * Cases du labyrinthe
	 */
	private ArrayList<ArrayList<Case>> cases;

	/**
	 * Jeu
	 */
	private ZeldiabloJeu jeu;

	/**
	 * Constructeur du labyrinthe
	 * @param jeu associe au labyrinthe
	 */
	public Labyrinthe(ZeldiabloJeu jeu) {
		this.initialiser();
		this.jeu = jeu;
	}

	/**
	 * Initialise le labyrinthe
	 */
	public void initialiser() {
		this.cases = new ArrayList<ArrayList<Case>>();

		/**
		 * LEGENDE :
		 * - 0 = case vide
		 * - 1 = mur
		 * - 2 = piege
		 * - 3 = portail
		 */
		int[][] casesTemplate = { 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1 ,1 }, 
				{ 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0 ,0 ,1 ,0 ,1 },
				{ 1, 0, 2, 1, 0, 1, 0, 0, 0, 0, 1 ,0 ,0 ,0 ,1 },
				{ 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 ,0 ,0 ,1 ,1 },
				{ 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0 ,0 ,0 ,0 ,1 },
				{ 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 ,0 ,1 ,0 ,1 },
				{ 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1 ,2 ,1 ,0 ,1 }, 
				{ 1, 0, 0, 1, 0, 0, 0, 3, 1, 0, 0 ,0 ,0 ,1 ,1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 ,0 ,0 ,0 ,1 },
				{ 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1 ,1 ,0 ,0 ,1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 ,0 ,0 ,0 ,1 }, 
				{ 1, 0, 1, 1, 0, 2, 0, 0, 1, 0, 0 ,0 ,1 ,1 ,1 },
				{ 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0 ,0 ,1 ,0 ,1 }, 
				{ 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1 ,0 ,0 ,0 ,1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1 ,1 ,1 ,1 }, };

		for (int i = 0; i < casesTemplate.length; i++) {
			this.cases.add(new ArrayList<Case>());

			for (int j = 0; j < casesTemplate[i].length; j++)  {
				if (casesTemplate[i][j] == 1) {
					this.cases.get(i).add(new CaseMur());
				} else if (casesTemplate[i][j] == 0){
					this.cases.get(i).add(new CaseVide());
				} else if (casesTemplate[i][j] == 2) {
					this.cases.get(i).add(new CasePiege());
				} else if (casesTemplate[i][j] == 3) {
					this.cases.get(i).add(new CasePortail());
				}
			}
		}
	}

	/**
	 * Recupere la case aux coordonnes donnees
	 * 
	 * @param x position x
	 * @param y position y
	 * @return case correspondante ou null si pas de case
	 */
	public Case getCase(int x, int y) {
		if (this.cases.get(y) != null) {
			return this.cases.get(y).get(x);
		}
		return null;
	}

	/**
	 * Recupere la largeur du labyrinthe
	 * 
	 * @return largeur du labyrinthe
	 */
	public int getLargeur() {
		if (this.cases.get(0) != null) {
			return this.cases.get(0).size();
		}
		return 0;
	}

	/**
	 * Recupere la hauteur du labyrinthe
	 * 
	 * @return hauteur du labyrinthe
	 */
	public int getHauteur() {
		return this.cases.size();
	}

	/**
	 * Coordonnees X du milieu
	 * 
	 * @return x du milieu
	 */
	public int getXMilieu() {
		return (int) Math.floor(this.getLargeur() / 2.0);

	}

	/**
	 * Coordonnees Y du milieu
	 * 
	 * @return y du milieu
	 */
	public int getYMilieu() {
		return (int) Math.floor(this.getHauteur() / 2.0);
	}

	/**
	 * Ajoute un mur aux coordonnees specifiees
	 * 
	 * @param px nombre a ajouter en x
	 * @param py nombre a ajouter en y
	 */
	public void ajouterMur(int px, int py) {
		this.cases.get(py).set(px, new CaseMur());
	}
	
	/**
	 * Ajoute un piege aux coordonnees specifiees 
	 * 
	 * @param px nombre a ajouter en x
	 * @param py nombre a ajouter en y
	 */
	public void ajouterPiege(int px, int py) {
		this.cases.get(py).set(px,  new CasePiege());
	}
	
	/**
	 * Methode retournant la case correspondant au portail
	 * @return cette case
	 */
	public CasePortail getPortail() {
		return (CasePortail) this.getCase(this.getXMilieu(), this.getYMilieu());
	}

	/**
	 * Representation du labyrinthe sous forme de chaine de caracteres
	 * 
	 * @return labyrinthe sous forme de chaine de caracteres
	 */
	public String toString() {
		String res = "";
		ArrayList<Entite> entites = this.jeu.getEntites();

		for (int i = 0; i < this.cases.size(); i++) {
			for (int j = 0; j < this.cases.get(i).size(); j++) {
				
				// Affichage des monstres
				int n = 0;
				boolean presenceMonstre = false;
				while (n != entites.size() && !presenceMonstre) {
					Entite entite = entites.get(n);
					if (entite.getX() == i && entite.getY() == j && !entite.etreMort() && !(entite instanceof Joueur)) {
						res += "o";
						presenceMonstre = true;
					}
					n += 1;
				}

				// Sinon affichage du joueur ou des cases
				if (!presenceMonstre) {
					Joueur joueur = this.jeu.getJoueur();
					if (joueur != null && joueur.getX() == i && joueur.getY() == j) {
						res += "+";
					} else if (this.cases.get(i).get(j).etreVide()) {
						res += " ";
					} else {
						res += ".";
					}
				}

			}
			res += "\n";
		}

		return res;
	}

}
