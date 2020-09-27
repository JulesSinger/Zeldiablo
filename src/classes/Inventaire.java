package classes;

import java.util.ArrayList;

import classes.objets.*;

/**
 * Represente l'inventaire du joueur
 */
public class Inventaire {
	/**
	 * Liste des objets dans l'inventaire
	 */
	private ArrayList<Objet> objets;

	/**
	 * Nombre d'objets max que l'inventaire peut posseder
	 */
	private static final int CAPACITE = 5;

	/**
	 * Constructeur par defaut
	 */
	public Inventaire() {
		this.objets = new ArrayList<Objet>(CAPACITE);
		this.objets.add(new Epee("Epee en Acier", 1));
		this.objets.add(new Arc("Arc de Robin", 1));
		this.objets.add(new PotionSoin("Potion de Soin", 1, 1));
	}

	/**
	 * Ajoute un objet dans l'inventaire
	 * 
	 * @param o objet
	 * @return ajoutValide 
	 */
	public boolean ajouterObjet(Objet o) {
		boolean ajoutValide = true;
		if (this.objets.size() < CAPACITE) {
			this.objets.add(o);
		} else {
			System.out.println("Inventaire plein.");
			ajoutValide = false;
		}
		return ajoutValide;
	}

	/**
	 * Supprime un objet de l'inventaire
	 * 
	 * @param index de l'objet a supprimer dans la liste
	 */
	public void supprimerObjet(int index) {
		this.objets.remove(index);
	}

	/**
	 * Recupere la liste d'objets dans l'inventaire
	 * 
	 * @return l'attribut objets
	 */
	public ArrayList<Objet> getObjets() {
		return this.objets;
	}


	/**
	 * Methode verifiant si l'amulette a ete ramassee ou non par le joueur
	 * 
	 * @return si l'amulette a ete ramassee ou non par le joueur
	 */
	public boolean etreRamasseeAmulette() {
		boolean b = false;
		for (int i = 0; i < this.objets.size(); i++) {
			if (this.objets.get(i) instanceof Amulette) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * Affichage formate de l'inventaire
	 */
	public String toString() {
		String res = "";
		int placesRestantes = CAPACITE - this.objets.size();
		res += "--------- Inventaire -----------\nCapacite restante : " + placesRestantes + "\n";
		for (int i = 0; i < this.objets.size(); i++) {
			res += "  " + (i + 1) + " : " + this.objets.get(i).getNom() + "\n";
		}
		res += "--------------------------------\n";
		return res;
	}
}