import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    private List<String> head;
    private List<List<Double>> colonnesNumeriques;

    public ReadCSV() {
        this.head = new ArrayList<>();
        this.colonnesNumeriques = new ArrayList<>();
    }

    public List<String> getHead() {
        return head;
    }

    public List<List<Double>> getColonnesNumeriques() {
        return colonnesNumeriques;
    }

    public void read(String fichierCSV) {
        head.clear();
        colonnesNumeriques.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(fichierCSV))) {
            String ligne;
            int lineIndex = 0;

            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split(",");

                if (lineIndex == 0) {
                    for (int i = 1; i < valeurs.length; i++) {
                        head.add(valeurs[i]);
                        colonnesNumeriques.add(new ArrayList<>());
                    }
                } else {
                    for (int i = 1; i < valeurs.length; i++) {
                        try {
                            double valeur = Double.parseDouble(valeurs[i]);
                            colonnesNumeriques.get(i - 1).add(valeur);
                        } catch (NumberFormatException e) {
                            System.err.println("Valeur non numérique ignorée : " + valeurs[i]);
                        }
                    }
                }
                lineIndex++;
            }

            System.out.println("→ Fichier lu avec succès : " + fichierCSV);

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + fichierCSV);
        }
    }
}
