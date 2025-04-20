package examen_finale;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Interface extends JFrame {
    private Stockage stockage; // Stockage avec une majuscule
    private JTextField titreField, auteurField, dateField, tailleField;
    private JTextArea affichageArea;
    private DefaultListModel<String> livreListModel;
    private JList<String> livreList;

    public Interface() {
        stockage = new Stockage(); // Instanciation correcte de Stockage

        setTitle("Gestion de Bibliothèque");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Titre:"));
        titreField = new JTextField();
        inputPanel.add(titreField);
        inputPanel.add(new JLabel("Auteur:"));
        auteurField = new JTextField();
        inputPanel.add(auteurField);
        inputPanel.add(new JLabel("Date:"));
        dateField = new JTextField();
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Taille fichier (Mo):"));
        tailleField = new JTextField();
        inputPanel.add(tailleField);

        livreListModel = new DefaultListModel<>();
        livreList = new JList<>(livreListModel);
        JScrollPane scrollPane = new JScrollPane(livreList);

        JPanel buttonPanel = new JPanel();
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(e -> ajouterLivre());
        buttonPanel.add(ajouterButton);

        JButton afficherButton = new JButton("Afficher");
        afficherButton.addActionListener(e -> afficherLivreDetails());
        buttonPanel.add(afficherButton);

        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(e -> {
            stockage.sauvegarderLivres("test.txt");
            affichageArea.append("Livres sauvegardés dans test.txt.\n");
        });
        buttonPanel.add(sauvegarderButton);

        affichageArea = new JTextArea();
        affichageArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.WEST);
        add(new JScrollPane(affichageArea), BorderLayout.SOUTH);

        setVisible(true);
    }

    private void ajouterLivre() {
        String titre = titreField.getText();
        String auteur = auteurField.getText();
        String date = dateField.getText();
        String taille = tailleField.getText();

        if (!taille.isEmpty()) {
            try {
                double tailleFichier = Double.parseDouble(taille);
                stockage.ajouterLivre(new LivreNumerique(titre, auteur, date, tailleFichier));
                livreListModel.addElement(titre + " (Numérique)");
            } catch (NumberFormatException ex) {
                affichageArea.append("Erreur: Taille du fichier invalide.\n");
            }
        } else {
            stockage.ajouterLivre(new LivrePapier(titre, auteur, date));
            livreListModel.addElement(titre + " (Papier)");
        }

        titreField.setText("");
        auteurField.setText("");
        dateField.setText("");
        tailleField.setText("");
    }

    private void afficherLivreDetails() {
        String selectedLivre = livreList.getSelectedValue();
        if (selectedLivre != null) {
            for (InterfaceAffiche livre : stockage.getLivres()) {
                if (livre instanceof LivrePapier && selectedLivre.contains("Papier")) {
                    LivrePapier lp = (LivrePapier) livre;
                    afficherDetailsFenetre(lp.getTitre(), lp.getAuteur(), lp.getDatePublication(), "Papier");
                } else if (livre instanceof LivreNumerique && selectedLivre.contains("Numérique")) {
                    LivreNumerique ln = (LivreNumerique) livre;
                    afficherDetailsFenetre(ln.getTitre(), ln.getAuteur(), ln.getDatePublication(), "Numérique", ln.getTailleFichier());
                }
            }
        }
    }

    private void afficherDetailsFenetre(String titre, String auteur, String date, String type) {
        JFrame detailsFrame = new JFrame("Détails du Livre");
        detailsFrame.setSize(400, 300);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setText("Titre: " + titre + "\nAuteur: " + auteur + "\nDate de Publication: " + date + "\nType: " + type);
        detailsArea.setEditable(false);

        detailsFrame.add(new JScrollPane(detailsArea));
        detailsFrame.setVisible(true);
    }

    private void afficherDetailsFenetre(String titre, String auteur, String date, String type, double taille) {
        JFrame detailsFrame = new JFrame("Détails du Livre");
        detailsFrame.setSize(400, 300);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea detailsArea = new JTextArea();
        detailsArea.setText("Titre: " + titre + "\nAuteur: " + auteur + "\nDate de Publication: " + date + "\nType: " + type + "\nTaille: " + taille + " Mo");
        detailsArea.setEditable(false);

        detailsFrame.add(new JScrollPane(detailsArea));
        detailsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Interface();
    }
}
