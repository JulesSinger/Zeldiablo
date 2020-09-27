package moteurJeu;

/**
 * permet de representer une commande de l'utilisateur
 * 
 * @author vthomas
 *
 */
public class Commande {

	/**
	 * boolean representant la commande de l'utilisateur
	 */
	public boolean gauche;
	public boolean droite;
	public boolean haut;
	public boolean bas;
	public boolean espace;
	public boolean ramasser;
	public boolean objet1;
	public boolean objet2;
	public boolean objet3;
	public boolean objet4;
	public boolean objet5;
	

	public Commande()
	{
		
	}
	
	/**
	 * constructeur par copie
	 * copie la commande pour en creer une nouvelle
	 * @param commandeACopier
	 */
	public Commande(Commande commandeACopier)
	{
		this.bas=commandeACopier.bas;
		this.haut=commandeACopier.haut;
		this.gauche=commandeACopier.gauche;
		this.droite=commandeACopier.droite;		
		this.espace = commandeACopier.espace;
		this.ramasser = commandeACopier.ramasser;
		this.objet1 = commandeACopier.objet1;
		this.objet2 = commandeACopier.objet2;
		this.objet3 = commandeACopier.objet3;
		this.objet4 = commandeACopier.objet4;
		this.objet5 = commandeACopier.objet5;
	}
	
}
