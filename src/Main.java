public class Main {
    public static void main(String[] args) {
        // Nom du fichier d'entrée (fichier CSV à analyser)
        String fichierCSV = "donnees.csv";

        // Nom des fichiers de sortie
        String rapportTexte = "statistiques.txt";
        String resumeCSV = "resume.csv";

        // Étape 1 : Lecture du fichier CSV
        ReadCSV reader = new ReadCSV();
        reader.read(fichierCSV);

        try {
            // Étape 2 : Calcul des statistiques (avec constructeur)
            Statistics stats = new Statistics(reader.getHead(), reader.getColonnesNumeriques());
            var resultats = stats.calculerStatistiques();

            // Étape 3 : Affichage des résultats dans la console
            System.out.println("Statistiques calculées :\n");
            for (int i = 1; i < resultats.size(); i++) { // i=1 pour ignorer l'en-tête
                String[] ligne = resultats.get(i);
                System.out.println("UA : " + ligne[0]);
                System.out.println("  Moyenne : " + ligne[1]);
                System.out.println("  Min     : " + ligne[2]);
                System.out.println("  Max     : " + ligne[3]);
                System.out.println("-".repeat(35));
            }

            // Étape 4 : Sauvegarde des résultats (avec constructeur)
            WriteCSV writerTxt = new WriteCSV(resultats, rapportTexte);
            writerTxt.saveTexte();

            WriteCSV writerCsv = new WriteCSV(resultats, resumeCSV);
            writerCsv.saveCSV();

            // Étape 5 : Confirmation
            System.out.println("→ Fichier lu avec succès : " + fichierCSV);
            System.out.println("→ Rapport texte généré : " + rapportTexte);
            System.out.println("→ Résumé CSV généré : " + resumeCSV);

        } catch (IllegalStateException e) {
            System.err.println("Données incomplètes ou absentes : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }
}
