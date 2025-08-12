import java.util.List;
import java.util.ArrayList;

public class Statistics {

    private List<String> head;
    private List<List<Double>> colonnesNumeriques;

    // Constructeur
    public Statistics(List<String> head, List<List<Double>> colonnesNumeriques) {
        if (head == null || colonnesNumeriques == null) {
            throw new IllegalArgumentException("Les données ne peuvent pas être null");
        }
        this.head = head;
        this.colonnesNumeriques = colonnesNumeriques;
    }

    // Méthode pour calculer les statistiques
    public List<String[]> calculerStatistiques() {
        if (head.isEmpty() || colonnesNumeriques.isEmpty()) {
            throw new IllegalStateException("Les données sont vides ou non initialisées");
        }

        List<String[]> resume = new ArrayList<>();
        resume.add(new String[]{"Colonne", "Moyenne", "Min", "Max"});

        for (int i = 0; i < head.size(); i++) {
            List<Double> col = colonnesNumeriques.get(i);
            double moyenne = col.stream().mapToDouble(Double::doubleValue).average().orElse(0);
            double min = col.stream().mapToDouble(Double::doubleValue).min().orElse(0);
            double max = col.stream().mapToDouble(Double::doubleValue).max().orElse(0);

            resume.add(new String[]{
                    head.get(i),
                    String.format("%.2f", moyenne),
                    String.format("%.2f", min),
                    String.format("%.2f", max)
            });
        }
        return resume;
    }
}
