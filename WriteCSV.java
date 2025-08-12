import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSV {

    private List<String[]> donnees;
    private String fichier;

    // Constructeur
    public WriteCSV(List<String[]> donnees, String fichier) {
        if (donnees == null || fichier == null || fichier.isBlank()) {
            throw new IllegalArgumentException("Les données et le nom du fichier ne peuvent pas être nuls ou vides");
        }
        this.donnees = donnees;
        this.fichier = fichier;
    }

    // Sauvegarde au format texte
    public void saveTexte() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fichier))) {
            for (int i = 1; i < donnees.size(); i++) {
                String[] ligne = donnees.get(i);
                pw.println("UA : " + ligne[0]);
                pw.println("  Moyenne : " + ligne[1]);
                pw.println("  Min     : " + ligne[2]);
                pw.println("  Max     : " + ligne[3]);
                pw.println("-".repeat(29));
            }
            System.out.println("→ Rapport texte généré : " + fichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du fichier : " + fichier);
        }
    }

    // Sauvegarde au format CSV
    public void saveCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fichier))) {
            for (String[] ligne : donnees) {
                pw.println(String.join(",", ligne));
            }
            System.out.println("→ Résumé CSV généré : " + fichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du fichier CSV : " + fichier);
        }
    }
}
