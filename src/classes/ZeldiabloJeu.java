package classes;

import java.util.ArrayList;

import classes.cases.Case;
import classes.cases.CaseMur;
import classes.cases.CasePiege;
import classes.cases.CasePortail;
import classes.entites.*;
import classes.objets.*;
import moteurJeu.Commande;
import moteurJeu.Jeu;

/**
 * Classe permettant la gestion et le fonctionnement du jeu dans sa globalite
 */
public class ZeldiabloJeu implements Jeu {

	/**
	 * Liste des entites dans le jeu
	 */
	private ArrayList<Entite> entites;

	/**
	 * Liste des objets dans le jeu
	 */
	private ArrayList<Objet> objets;

	/**
	 * Labyrinthe (espace de jeu)
	 */
	private Labyrinthe labyrinthe;

	/**
	 * Constructeur de la classe Jeu initialisant les attributs joueur et labyrinthe
	 * de l'objet Jeu
	 */
	public ZeldiabloJeu() {
		this.labyrinthe = new Labyrinthe(this);
		this.entites = new ArrayList<Entite>();
		this.entites.add(new Joueur(this.labyrinthe.getXMilieu(), this.labyrinthe.getYMilieu()));

		this.initialiserMonstres();
		this.initialiserObjets();
	}

	/**
	 * Initialise les monstres dans le jeu
	 */
	public void initialiserMonstres() {
		// Initialisation des monstres de base
		int nbMonstres = 3; // nombre de monstres a initialiser
		for (int i = 0; i < nbMonstres; i++) {
			int[] caseMonstre = this.chercherCaseVide();
			this.entites.add(new Monstre(caseMonstre[0], caseMonstre[1], 1));

		}

		// Initialisation des fantomes
		int nbFantomes = 2; // nombre de fantomes a initialiser
		for (int j = 0; j < nbFantomes; j++) {
			int aleaX = (int) (Math.random() * this.labyrinthe.getLargeur());
			int aleaY = (int) (Math.random() * this.labyrinthe.getLargeur());
			this.entites.add(new Fantome(aleaX, aleaY, 1));
		}
		
		// Initialisation des Gobelins
		int nbGobelins = 3; // nombre de gobelins a initialiser
			for (int k = 0; k < nbGobelins; k++) {
				int[] caseMonstre = this.chercherCaseVide();
				this.entites.add(new Gobelin(caseMonstre[0], caseMonstre[1], 2));
			}
	}

	/**
	 * Initialise les objets dans le jeu
	 */
	public void initialiserObjets() {
		this.objets = new ArrayList<Objet>();

		// Ajout des potions de soin
		int nbPotions = 3;
		for (int i = 0; i < nbPotions; i++) {
			int[] casePotion = this.chercherCaseVide();
			this.objets.add(new PotionSoin("Potion de Soin", casePotion[0], casePotion[1]));
		}
		
		// Ajout d'une potion de force
		int[] casePotion = this.chercherCaseVide();
		this.objets.add(new PotionForce("Potion de Force", casePotion[0], casePotion[1]));

		// Ajout de l'amulette
		int[] caseAmulette = this.chercherCaseVide();
		this.objets.add(new Amulette("Amulette", caseAmulette[0], caseAmulette[1]));
	}

	/**
	 * Active tous les pieges
	 */
	public void activerLesPieges() {
		for (int i = 0; i < this.getLabyrinthe().getHauteur(); i++) {
			for (int j = 0; j < this.getLabyrinthe().getLargeur(); j++) {
				if (this.getLabyrinthe().getCase(i, j) instanceof CasePiege) {
					((CasePiege) this.getLabyrinthe().getCase(i, j)).etreActif(true);
				}
			}
		}
	}

	/**
	 * Verifie si la case aux coordonnees donnees est un piege
	 * 
	 * @param x abscisse de la case
	 * @param y ordonnee de la case
	 * @return si la case est un piege ou non
	 */
	public boolean etrePiege(int x, int y) {
		Labyrinthe labyrinthe = this.getLabyrinthe();

		return labyrinthe.getCase(x, y) instanceof CasePiege && ((CasePiege) labyrinthe.getCase(x, y)).getEtreActif();
	}

	/**
	 * Fais perdre la vie au joueur si c'est un piege
	 */
	public void perdreViePiege() {
		if (etrePiege(this.getJoueur().getX(), this.getJoueur().getY())) {
			Joueur joueur = this.getJoueur();
			joueur.setVie(joueur.getVie() - 1);
			CasePiege piege = (CasePiege) this.getLabyrinthe().getCase(joueur.getX(), joueur.getY());
			piege.etreActif(false);
		}
	}

	/**
	 * Recupere le labyrinthe du jeu
	 * 
	 * @return le labyrinthe
	 */
	public Labyrinthe getLabyrinthe() {
		return this.labyrinthe;
	}

	/**
	 * Recupere le joueur associe au jeu
	 * 
	 * @return le joueur
	 */
	public Joueur getJoueur() {
		Joueur j = null;
		Entite e = null;

		int i = 0;
		while (i < this.entites.size() && j == null) {
			e = this.entites.get(i);
			if (e instanceof Joueur) {
				j = (Joueur) e;
			}
			i++;
		}

		return j;
	}

	/**
	 * Verifie si le joueur est sur le portail
	 * 
	 * @return si le joueur est sur le portail ou non
	 */
	public boolean etreSurPortail() {
		Joueur j = this.getJoueur();
		Labyrinthe labyrinthe = this.getLabyrinthe();

		return ((j.getX() == labyrinthe.getXMilieu()) && (j.getY() == labyrinthe.getYMilieu()));
	}

	/**
	 * Renvoit les entites presentes dans le jeu
	 * 
	 * @return liste des entites
	 */
	public ArrayList<Entite> getEntites() {
		return this.entites;
	}

	/**
	 * Renvoit les objets presents dans le jeu
	 * 
	 * @return liste des objets
	 */
	public ArrayList<Objet> getObjets() {
		return this.objets;
	}

	/**
	 * verifie si les deux entites sont adjacentes dans le labyrinthe
	 * 
	 * @param e1 premiere entite
	 * @param e2 deuxieme entite
	 * @return si les entites sont adjacentes ou non
	 */
	public boolean etreAdjacent(Entite e1, Entite e2) {
		return (e1.getX() - 1 == e2.getX() && e1.getY() == e2.getY())
				|| (e1.getX() + 1 == e2.getX() && e1.getY() == e2.getY())
				|| (e1.getX() == e2.getX() && e1.getY() - 1 == e2.getY())
				|| (e1.getX() == e2.getX() && e1.getY() + 1 == e2.getY());
	}

	/**
	 * Verifie si un mur est rencontre a droite du joueur
	 * 
	 * @param nbCases : nombre de cases de distance a verifier
	 * @return l'entier correspondant a la distance qu'un mur est rencontre
	 */
	public int rencontrerMurDroite(int nbCases) {
		int indexMurRencontre = 99;

		if (this.getJoueur().getX() == 13) {
			nbCases = 1;
		} else if (this.getJoueur().getX() == 12) {
			nbCases = 2;
		}

		for (int i = 1; i <= nbCases; i++) {
			if (this.getLabyrinthe().getCase(this.getJoueur().getX() + i, this.getJoueur().getY()) instanceof CaseMur) {
				indexMurRencontre = i;	
				break;
			}
		}

		return indexMurRencontre;
	}

	/**
	 * Verifie si un mur est rencontre a gauche du joueur
	 * 
	 * @param nbCases : nombre de cases de distance a verifier
	 * @return l'entier correspondant a la distance qu'un mur est rencontre
	 */
	public int rencontrerMurGauche(int nbCases) {
		int indexMurRencontre = 99;
		if (this.getJoueur().getX() == 1) {
			nbCases = 1;
		} else if (this.getJoueur().getX() == 2) {
			nbCases = 2;
		}

		for (int i = 1; i <= nbCases; i++) {
			if (this.getLabyrinthe().getCase(this.getJoueur().getX() - i, this.getJoueur().getY()) instanceof CaseMur) {
				indexMurRencontre = i;
				break;
			}
		}

		return indexMurRencontre;
	}

	/**
	 * Verifie si un mur est rencontre en contrebas du joueur
	 * 
	 * @param nbCases : nombre de cases de distance a verifier
	 * @return l'entier correspondant a la distance qu'un mur est rencontre
	 */
	public int rencontrerMurBas(int nbCases) {
		int indexMurRencontre = 99;

		if (this.getJoueur().getY() == 13) {
			nbCases = 1;
		} else if (this.getJoueur().getY() == 12) {
			nbCases = 2;
		}

		for (int i = 1; i <= nbCases; i++) {
			if (this.getLabyrinthe().getCase(this.getJoueur().getX(), this.getJoueur().getY() + i) instanceof CaseMur) {
				indexMurRencontre = i;
				break;
			}
		}

		return indexMurRencontre;
	}

	/**
	 * Verifie si un mur est rencontre en contrebas du joueur
	 * 
	 * @param nbCases : nombre de cases de distance a verifier
	 * @return l'entier correspondant a la distance qu'un mur est rencontre
	 */
	public int rencontrerMurHaut(int nbCases) {
		int indexMurRencontre = 99;

		if (this.getJoueur().getY() == 1) {
			nbCases = 1;
		} else if (this.getJoueur().getY() == 2) {
			nbCases = 2;
		}

		for (int i = 1; i <= nbCases; i++) {
			if (this.getLabyrinthe().getCase(this.getJoueur().getX(), this.getJoueur().getY() - i) instanceof CaseMur) {
				indexMurRencontre = i;
				break;
			}
		}

		return indexMurRencontre;
	}

	/**
	 * Verifie si les deux entites sont a une distance maximale de 3 cases.
	 * 
	 * @param e1 premiere entite
	 * @param e2 deuxieme entite
	 * @return si les deux entites sont a proximite ou non
	 */
	public boolean etreAProximite(Entite e1, Entite e2) {
		boolean estAProximite = false;
		
		if(rencontrerMurGauche(3) >= 4 && ((e2.getX() == e1.getX() - 1 || e2.getX() == e1.getX() - 2 || e2.getX() == e1.getX() - 3)
				&& e2.getY() == e1.getY())){
			estAProximite = true;
		} else if(rencontrerMurGauche(3) == 3 && ((e2.getX() == e1.getX() - 1 || e2.getX() == e1.getX() - 2)
				&& e2.getY() == e1.getY())){
			estAProximite = true;
		} else if(rencontrerMurGauche(3) == 2 && ((e2.getX() == e1.getX() - 1)
				&& e2.getY() == e1.getY())){
			estAProximite = true;
		}
		
		if(rencontrerMurDroite(3) >= 4 && ((e2.getX() == e1.getX() + 1 || e2.getX() == e1.getX() + 2 || e2.getX() == e1.getX() + 3)
				&& e2.getY() == e1.getY())){
			estAProximite = true;
		} else if(rencontrerMurDroite(3) == 3 && ((e2.getX() == e1.getX() + 1 || e2.getX() == e1.getX() + 2)
				&& e2.getY() == e1.getY())){
			estAProximite = true;
		} else if(rencontrerMurDroite(3) == 2 && ((e2.getX() == e1.getX() + 1)
				&& e2.getY() == e1.getY())){
			estAProximite = true;
		}
		
		if(rencontrerMurBas(3) >= 4 && ((e2.getY() == e1.getY() + 1 || e2.getY() == e1.getY() + 2 || e2.getY() == e1.getY() + 3)
				&& e2.getX() == e1.getX())){
			estAProximite = true;
		} else if(rencontrerMurGauche(3) == 3 && ((e2.getY() == e1.getY() + 1 || e2.getY() == e1.getY() + 2)
				&& e2.getX() == e1.getX())){
			estAProximite = true;
		} else if(rencontrerMurGauche(3) == 2 && ((e2.getY() == e1.getY() + 1)
				&& e2.getX() == e1.getX())){
			estAProximite = true;
		}
		
		if(rencontrerMurHaut(3) >= 4 && ((e2.getY() == e1.getY() - 1 || e2.getY() == e1.getY() - 2 || e2.getY() == e1.getY() - 3)
				&& e2.getX() == e1.getX())){
			estAProximite = true;
		} else if(rencontrerMurHaut(3) == 3 && ((e2.getY() == e1.getY() - 1 || e2.getY() == e1.getY() - 2)
				&& e2.getX() == e1.getX())){
			estAProximite = true;
		} else if(rencontrerMurHaut(3) == 2 && ((e2.getY() == e1.getY() - 1)
				&& e2.getX() == e1.getX())){
			estAProximite = true;
		}
		
		return estAProximite;
	}

	/**
	 * Pour tous les monstres, verifie si une attaque du monstre et possible et
	 * attaque le joueur si ca l'est.
	 */
	public void attaquerLeJoueur() {
		Joueur j = this.getJoueur();

		for (int i = 0; i < this.getEntites().size(); i++) {
			Entite e = this.getEntites().get(i);

			if (!(e instanceof Joueur) && this.etreAdjacent(e, j)) {
				e.attaquer(j, ((Monstre)e).getPuissance());
			}
		}
	}

	/**
	 * Attaque les monstres selon l'arme qui sera selectionnee
	 * @param armeSelectionnee l'arme en cours d'utilisation
	 */
	public void attaquerLesMonstres(Arme armeSelectionnee) {
		if (armeSelectionnee instanceof Epee) {
			for (int i = 0; i < this.entites.size(); i++) {
				if ((!(this.entites.get(i) instanceof Joueur) && !this.entites.get(i).etreMort()
						&& etreAdjacent(this.getJoueur(), this.entites.get(i)))) {
					this.getJoueur().attaquer(this.entites.get(i), armeSelectionnee);
				}
			}
		} else {
			for (int i = 0; i < this.entites.size(); i++) {
				if ((!(this.entites.get(i) instanceof Joueur) && !this.entites.get(i).etreMort()
						&& etreAProximite(this.getJoueur(), this.entites.get(i)))) {
					this.getJoueur().attaquer(this.entites.get(i), armeSelectionnee);
					break;
				}
			}
		}
	}

	/**
	 * Cherche l'arme selectionnee
	 * 
	 * @return l'arme selectionnee par le joueur
	 */
	public Arme chercherArmeSelectionnee() {
		Arme armeSelectionnee = null;
		ArrayList<Objet> objets = this.getJoueur().getInventaire().getObjets();

		// si l'arc est equipe
		if (((Arc) objets.get(1)).etreEquipee()) {
			// alors l'arme selectionnee pour l'attaque est l'arc
			armeSelectionnee = (Arc) objets.get(1);
		} else {
			// sinon c'est l'eppee
			armeSelectionnee = (Epee) objets.get(0);
		}

		return armeSelectionnee;
	}

	/**
	 * Deplace le joueur selon la direction en parametre
	 * 
	 * @param direction : direction dans laquelle le joueur va se deplacer
	 */
	public void deplacerJoueur(String direction) {
		Joueur j = (Joueur) this.getJoueur();

		switch (direction) {
		case "left":
			if (this.validerDeplacement(-1, 0, j))
				j.seDeplacer(-1, 0);
			j.setLastX(j.getX() + 1);
			j.setLastY(j.getY());
			break;
		case "right":
			if (this.validerDeplacement(1, 0, j))
				j.seDeplacer(1, 0);
			j.setLastX(j.getX() - 1);
			j.setLastY(j.getY());
			break;
		case "up":
			if (this.validerDeplacement(0, -1, j))
				j.seDeplacer(0, -1);
			j.setLastX(j.getX());
			j.setLastY(j.getY() + 1);

			break;
		case "down":
			if (this.validerDeplacement(0, 1, j))
				j.seDeplacer(0, 1);
			j.setLastX(j.getX());
			j.setLastY(j.getY() - 1);
			break;

		default:
			break;
		}
	}

	/**
	 * Deplace les monstres dans le labyrinthe
	 */
	public void deplacerMonstresAleatoirement() {
		int alea;
		for (int i = 0; i < this.getEntites().size(); i++) {
			Entite e = this.getEntites().get(i);

			if (!(e instanceof Joueur)) {
				alea = (int) Math.round(Math.random() * 4);
				switch (alea) {
				case 1:
					if (this.validerDeplacement(1, 0, e))
						e.seDeplacer(1, 0);
					break;
				case 2:
					if (this.validerDeplacement(0, 1, e))
						e.seDeplacer(0, 1);
					break;
				case 3:
					if (this.validerDeplacement(-1, 0, e))
						e.seDeplacer(-1, 0);
					break;
				case 4:
					if (this.validerDeplacement(0, -1, e))
						e.seDeplacer(0, -1);
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * Verifie si le deplacement d'une entite est valide
	 * 
	 * @param px deplacement de px cases
	 * @param py deplacement de py cases
	 * @param e Entite a valider
	 * @return si le deplacement est valide ou non
	 */
	public boolean validerDeplacement(int px, int py, Entite e) {
		int posX = e.getX() + px;
		int posY = e.getY() + py;
		boolean ok = false;

		Labyrinthe labyrinthe = this.getLabyrinthe();
		ArrayList<Entite> entites = this.getEntites();

		if (e instanceof Fantome) {
			if (posX >= 0 && posY >= 0 && posX < labyrinthe.getLargeur() && posY < labyrinthe.getLargeur()) {
				ok = true;
			}
		} else {
			ok = labyrinthe.getCase(posX, posY).etreVide();
		}

		for (int i = 0; i < entites.size(); i++) {
			Entite ent = entites.get(i);
			if ((ent.getX() == posX) && (ent.getY() == posY) && !(ent.etrePermeable()) && !ent.etreMort()) {
				ok = false;
			}
		}

		return ok;
	}

	/**
	 * Evolution du jeu (deplacements, attaques...)
	 * 
	 * @param commandeUser, commande de l'utilisateur
	 */
	@Override
	public void evoluer(Commande commandeUser) {
		// Deplacer le joueur
		if (commandeUser.gauche) {
			this.deplacerJoueur("left");
			this.deplacerMonstresAleatoirement();
			this.attaquerLeJoueur();
		} else if (commandeUser.droite) {
			this.deplacerJoueur("right");
			this.deplacerMonstresAleatoirement();
			this.attaquerLeJoueur();
		} else if (commandeUser.haut) {
			this.deplacerJoueur("up");
			this.deplacerMonstresAleatoirement();
			this.attaquerLeJoueur();
		} else if (commandeUser.bas) {
			this.deplacerJoueur("down");
			this.deplacerMonstresAleatoirement();
			this.attaquerLeJoueur();
		} else if (commandeUser.espace) {
			this.attaquerLesMonstres(this.chercherArmeSelectionnee());
			this.deplacerMonstresAleatoirement();
		} else if (commandeUser.ramasser) {
			this.deplacerMonstresAleatoirement();
			this.attaquerLeJoueur();
			this.ramasserObjet();
		} else if (commandeUser.objet1) {
			((Arme) this.getJoueur().getInventaire().getObjets().get(1)).equiperArme(false);
			((Arme) this.getJoueur().getInventaire().getObjets().get(0)).equiperArme(true);
			System.out.println("Changement d'arme : Eppée Selectionnée");
		} else if (commandeUser.objet2) {
			((Arme) this.getJoueur().getInventaire().getObjets().get(0)).equiperArme(false);
			((Arme) this.getJoueur().getInventaire().getObjets().get(1)).equiperArme(true);
			System.out.println("Changement d'arme : Arc Selectionnée");
		} else if (commandeUser.objet3)
			this.utiliserObjet(3);
		else if (commandeUser.objet4)
			this.utiliserObjet(4);
		else if (commandeUser.objet5)
			this.utiliserObjet(5);

		// Affichage de l'inventaire
		if (commandeUser.gauche || commandeUser.droite || commandeUser.bas || commandeUser.haut) {
			this.afficherInventaire();
		}

		// Activation des pieges si necessaire
		if ((commandeUser.gauche || commandeUser.droite || commandeUser.bas || commandeUser.haut)
				&& this.getLabyrinthe().getCase(this.getJoueur().getLastX(),
						this.getJoueur().getLastY()) instanceof CasePiege) {
			this.activerLesPieges();
		}

		// Si le joueur est sur un piege, il perd de la vie
		this.perdreViePiege();
	}

	/**
	 * Ramasse un objet lorsque les conditions sont valides
	 * 
	 */
	public void ramasserObjet() {
		Joueur joueur = this.getJoueur();
		Inventaire inventaire = joueur.getInventaire();

		for (int i = 0; i < this.getObjets().size(); i++) {
			Objet o = this.getObjets().get(i);

			if (o.getX() == joueur.getX() && o.getY() == joueur.getY()) {
				boolean validationAjout = inventaire.ajouterObjet(o); // ajoute l'objet dans l'inventaire du joueur
				if (validationAjout)
					this.getObjets().remove(i); // supprime l'objet dans le jeu
				break;
			}
		}
	}

	/**
	 * Utilise un objet dans l'inventaire du joueur
	 * 
	 * @param numero de l'objet
	 */
	public void utiliserObjet(int numero) {
		ArrayList<Objet> lObjets = this.getJoueur().getInventaire().getObjets();
		Joueur joueur = this.getJoueur();
		Inventaire inventaire = joueur.getInventaire();

		if (lObjets.size() > numero - 1 && lObjets.get(numero - 1) instanceof Potion) {
			Potion potion = (Potion) lObjets.get(numero - 1);
			potion.utiliser(joueur);
			inventaire.supprimerObjet(numero - 1);
		}

	}

	/**
	 * Affiche l'inventaire du joueur
	 */
	private void afficherInventaire() {
		System.out.println(this.getJoueur().getInventaire());
	}

	/**
	 * Indique si le jeu est fini ou non
	 * 
	 * @return si le jeu est fini ou non
	 */
	@Override
	public boolean etreFini() {
		Joueur joueur = this.getJoueur();
		CasePortail portail = this.getLabyrinthe().getPortail();

		// Active le portail si le joueur possede l'amulette
		if (joueur.getInventaire().etreRamasseeAmulette()) {
			portail.etreActif(true);
		}

		// Gagne si le joueur est sur le portail avec l'amulette, perdu s'il est mort
		if (portail.getEtreActif() && this.etreSurPortail()) {
			return true;
		} else {
			return joueur.etreMort();
		}
	}

	/**
	 * Cherche une case vide disponible aleatoirement dans le labyrinthe
	 * 
	 * @return les coordonnees de la case vide disponible
	 */
	private int[] chercherCaseVide() {
		Joueur j = this.getJoueur();
		Case c = null;
		int[] coordonnees = new int[2];

		while (true) {
			int aleaX = (int) (Math.random() * this.labyrinthe.getLargeur());
			int aleaY = (int) (Math.random() * this.labyrinthe.getLargeur());
			c = this.labyrinthe.getCase(aleaX, aleaY);

			if (c.etreVide() && (aleaX != j.getX() || aleaY != j.getY())) {
				coordonnees[0] = aleaX;
				coordonnees[1] = aleaY;
				break;
			}
		}

		return coordonnees;
	}
}
