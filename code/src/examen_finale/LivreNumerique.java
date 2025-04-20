package examen_finale;

public class LivreNumerique implements InterfaceAffiche {
    private String titre;
    private String auteur;
    private String datePublication;
    private double tailleFichier;

    public LivreNumerique(String titre, String auteur, String datePublication, double tailleFichier) {
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.tailleFichier = tailleFichier;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public double getTailleFichier() {
        return tailleFichier;
    }

    public void affichageDetails() {
        System.out.println("Le titre du livre numérique est : " + titre + ", de l'auteur : " + auteur + ", date de publication : " + datePublication + ", taille : " + tailleFichier + " Mo");
    }
}
