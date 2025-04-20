package examen_finale;

import java.io.*;
import java.util.*;

public class Stockage {
    private List<InterfaceAffiche> livres;

    public Stockage() {
        livres = new ArrayList<>();
    }

    public void ajouterLivre(InterfaceAffiche livre) {
        livres.add(livre);
    }

    public List<InterfaceAffiche> getLivres() {
        return livres;
    }

    public void sauvegarderLivres(String nomFichier) {
        try {
            PrintWriter sortie = new PrintWriter(new FileWriter(nomFichier));

            for (InterfaceAffiche livre : livres) {
                if (livre instanceof LivrePapier) {
                    LivrePapier lp = (LivrePapier) livre;
                    sortie.println("Papier;" + lp.getTitre() + ";" + lp.getAuteur() + ";" + lp.getDatePublication());
                } else if (livre instanceof LivreNumerique) {
                    LivreNumerique ln = (LivreNumerique) livre;
                    sortie.println("Numerique;" + ln.getTitre() + ";" + ln.getAuteur() + ";" + ln.getDatePublication() + ";" + ln.getTailleFichier());
                }
            }

            sortie.close();
            System.out.println("Les livres ont été sauvegardés dans le fichier " + nomFichier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
