package classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import moteurJeu.DessinJeu;
import classes.ZeldiabloJeu;
import classes.cases.*;
import classes.entites.*;
import classes.objets.*;

/**
 * Classe permettant l'affichage graphique du jeu
 */
public class ZeldiabloDessinJeu implements DessinJeu {
	/**
	 * Taille d'une case
	 */
	public static final int TAILLE = 50;

	/**
	 * Jeu
	 */
	private ZeldiabloJeu jeu;

	/**
	 * Constructeur dessin du jeu
	 * 
	 * @param j jeu
	 */
	public ZeldiabloDessinJeu(ZeldiabloJeu j) {
		this.jeu = j;
	}

	@Override
	public void dessiner(BufferedImage image) {
		Graphics2D g = (Graphics2D) image.getGraphics();

		if (!this.jeu.etreFini()) {
			try {
				this.dessinerJeu(g);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (this.jeu.getJoueur().etreMort()) {
			this.dessinerPerdu(g);
		} else {
			this.dessinerGagne(g);
		}
	}

	/**
	 * Dessine le jeu en partie
	 * 
	 * @param g objet graphique
	 * @throws IOException
	 */
	private void dessinerJeu(Graphics2D g) throws IOException {
		Labyrinthe l = this.jeu.getLabyrinthe();
		int w = l.getLargeur();
		int h = l.getHauteur();

		Entite joueur = this.jeu.getJoueur();

		// Affichage des points de vies
		g.setColor(Color.BLACK);
		g.setFont(new Font("Verdana", Font.BOLD, 32));
		g.drawString("Points de vie :", (w * TAILLE) / 2 - 4 * TAILLE, 35);
		g.drawString(" " + Integer.toString(joueur.getVie()), (w * TAILLE) - TAILLE * 6, 37);

		// Affichage des cases
		g.setColor(Color.BLACK);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Case c = l.getCase(i, j);

				if (!c.etreVide()) {
					BufferedImage img = ImageIO.read(new File("img/Mur/Mur.png"));
					g.drawImage(img, i * TAILLE, j * TAILLE + TAILLE, null);
				} else if (c instanceof CasePiege && ((CasePiege) c).getEtreActif()) { // pieges
					BufferedImage img = ImageIO.read(new File("img/Piege.png"));
					g.drawImage(img, i * TAILLE, j * TAILLE + TAILLE, null);
				} else if (c instanceof CasePortail) { // portail
					BufferedImage img = ImageIO.read(new File("img/Sortie.png"));
					g.drawImage(img, i * TAILLE, j * TAILLE + TAILLE, null);
				}
			}
		}

		// Affichage des monstres
		ArrayList<Entite> entites = this.jeu.getEntites();
		for (Entite e : entites) {
			if (e.getVie() > 0) {
				if (e instanceof Joueur) {
					BufferedImage img = ImageIO.read(new File("img/Personnage/Bas.png"));
					g.drawImage(img, e.getX() * TAILLE, e.getY() * TAILLE + TAILLE, null);
				} else if (e instanceof Fantome) {
					BufferedImage img = ImageIO.read(new File("img/Monstre/Fantome/Bas.png"));
					g.drawImage(img, e.getX() * TAILLE, e.getY() * TAILLE + TAILLE, null);
				} else if (e instanceof Gobelin) {
					BufferedImage img = ImageIO.read(new File("img/Monstre/Gobelin/Bas.png"));
					g.drawImage(img, e.getX() * TAILLE, e.getY() * TAILLE + TAILLE, null);
				} else if (e instanceof Monstre) {
					BufferedImage img = ImageIO.read(new File("img/Monstre/Orc/Bas.png"));
					g.drawImage(img, e.getX() * TAILLE, e.getY() * TAILLE + TAILLE, null);
				} 
			}
		}

		// Affichage des objets au sol
		ArrayList<Objet> objets = this.jeu.getObjets();
		for (Objet o : objets) {
			if (o instanceof Amulette) {
				BufferedImage img = ImageIO.read(new File("img/Cle.png"));
				g.drawImage(img, o.getX() * TAILLE, o.getY() * TAILLE + TAILLE, null);
			} else if (o instanceof PotionSoin) {
				BufferedImage img = ImageIO.read(new File("img/PotionSoin.png"));
				g.drawImage(img, o.getX() * TAILLE, o.getY() * TAILLE + TAILLE, null);
			} else if (o instanceof PotionForce) {
				BufferedImage img = ImageIO.read(new File("img/PotionForce.png"));
				g.drawImage(img, o.getX() * TAILLE, o.getY() * TAILLE + TAILLE, null);
			}
		}

		g.dispose();
	}

	/**
	 * Dessine le jeu quand le joueur a perdu
	 * 
	 * @param g objet graphique
	 */
	private void dessinerPerdu(Graphics2D g) {
		Labyrinthe l = this.jeu.getLabyrinthe();
		int w = l.getLargeur();
		int h = l.getHauteur();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w * TAILLE, h * TAILLE);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", Font.BOLD, 32));
		g.drawString("GAME OVER", (w * TAILLE) / 2 - 2 * TAILLE, (h * TAILLE) / 2 + TAILLE);
	}

	/**
	 * Dessine le jeu quand le joueur a gagnes
	 * 
	 * @param g objet graphique
	 */
	private void dessinerGagne(Graphics2D g) {
		Labyrinthe l = this.jeu.getLabyrinthe();
		int w = l.getLargeur();
		int h = l.getHauteur();

		g.setColor(Color.GREEN);
		g.fillRect(0, 0, w * TAILLE, h * TAILLE + TAILLE);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", Font.BOLD, 32));
		g.drawString("VICTOIRE", (w * TAILLE) / 2 - 2 * TAILLE, (h * TAILLE) / 2 + TAILLE);
	}
}
