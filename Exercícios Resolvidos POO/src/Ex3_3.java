import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

public class Ex3_3 {
    public static void main(String[] args) {
        // Removido o 'õ' para evitar erros de encoding
        int[] dimensoes = {1000, 5000, 10000, 20000, 40000, 100000};

        // Usamos apenas os nomes para evitar problemas com arrays de genéricos
        String[] nomesColecoes = {"ArrayList", "LinkedList", "HashSet", "TreeSet"};

        System.out.println("Collection\t1000\t5000\t10000\t20000\t40000\t100000");

        for (String nomeColecao : nomesColecoes) {
            System.out.println(nomeColecao);

            double[] temposAdd = new double[dimensoes.length];
            double[] temposSearch = new double[dimensoes.length];
            double[] temposRemove = new double[dimensoes.length];

            for (int i = 0; i < dimensoes.length; i++) {
                // Instancia uma coleção limpa em cada iteração
                Collection<Integer> col = instanciarColecao(nomeColecao);
                Map<String, Double> res = checkPerformance(col, dimensoes[i]);

                temposAdd[i] = res.get("add");
                temposSearch[i] = res.get("search");
                temposRemove[i] = res.get("remove");
            }

            imprimirLinha("add", temposAdd);
            imprimirLinha("search", temposSearch);
            imprimirLinha("remove", temposRemove);
        }
    }

    private static Collection<Integer> instanciarColecao(String nome) {
        switch (nome) {
            case "ArrayList": return new ArrayList<>();
            case "LinkedList": return new LinkedList<>();
            case "HashSet": return new HashSet<>();
            case "TreeSet": return new TreeSet<>();
            default: return new ArrayList<>();
        }
    }

    private static void imprimirLinha(String operacao, double[] tempos) {
        System.out.print(operacao + "\t\t");
        for (double tempo : tempos) {
            System.out.printf(Locale.US, "%.2f\t", tempo);
        }
        System.out.println();
    }

    private static Map<String, Double> checkPerformance(Collection<Integer> col, final int DIM) {
        double start, stop, delta;
        Map<String, Double> out = new HashMap<>(); [cite_start]// [cite: 39]

        [cite_start]// Add [cite: 40]
        start = System.nanoTime(); [cite_start]// [cite: 41]
        [cite_start]for (int i = 0; i < DIM; i++) // [cite: 42]
            col.add(i); [cite_start]// [cite: 43]
        stop = System.nanoTime();  [cite_start]// [cite: 44]
        delta = (stop - start) / 1e6; [cite_start]// [cite: 45]
        out.put("add", delta); [cite_start]// [cite: 46]

        [cite_start]// Search [cite: 47]
        start = System.nanoTime(); [cite_start]// [cite: 48]
        [cite_start]for (int i = 0; i < DIM; i++) { // [cite: 49]
            int n = (int) (Math.random() * DIM); [cite_start]// [cite: 51]
            [cite_start]if (!col.contains(n)) // [cite: 52]
                System.out.println("Not found???" + n); [cite_start]// [cite: 53]
        }
        stop = System.nanoTime(); [cite_start]// [cite: 54]
        delta = (stop - start) / 1e6; [cite_start]// [cite: 55]
        out.put("search", delta); [cite_start]// [cite: 55]

        [cite_start]// Remove [cite: 56]
        start = System.nanoTime(); [cite_start]// [cite: 57]
        Iterator<Integer> iterator = col.iterator(); [cite_start]// [cite: 58]
        [cite_start]while (iterator.hasNext()) { // [cite: 59]
            iterator.next(); [cite_start]// [cite: 61]
            iterator.remove(); [cite_start]// [cite: 62]
        }
        stop = System.nanoTime(); [cite_start]// [cite: 64]
        delta = (stop - start) / 1e6; [cite_start]// [cite: 65]
        out.put("remove", delta); [cite_start]// [cite: 66]

        return out; [cite_start]// [cite: 67]
    }
}