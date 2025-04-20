package examen_finale;

public class LivrePapier implements InterfaceAffiche {
    private String titre;
    private String auteur;
    private String datePublication;

    public LivrePapier(String titre, String auteur, String datePublication) {
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
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

    
    public void affichageDetails() {
        System.out.println("Le titre du livre papier : " + titre + ", d'auteur : " + auteur + ", et la date de publication : " + datePublication);
    }
}
