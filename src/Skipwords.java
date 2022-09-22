import java.io.*;
import java.util.*;

public class Skipwords {


    public static void main(String[] args) {

        List<String> palabrasVacias = lineasDeArchivo("stopwords-es.txt");
        List<String> lineasDeLibro = lineasDeArchivo("divina_comedia.txt");

        String delimitadores = "\\s+|,\\s*|\\.\\s*|\\;\\s*|\\:\\s*|\\!\\s*|\\¡\\s*|\\¿\\s*|\\?\\s*|\\-\\s*"
                + "|\\[\\s*|\\]\\s*|\\(\\s*|\\)\\s*|\\\"\\s*|\\_\\s*|\\%\\s*|\\+\\s*|\\/\\s*|\\#\\s*|\\$\\s*|\\d+";

        List<String> palabrasDeLibro = new ArrayList<>();
        for (String linea : lineasDeLibro) {
            String[] palabrasDeLinea = linea.split(delimitadores);
            for (String palabra : palabrasDeLinea) {
                palabrasDeLibro.add(palabra);
            }
        }

        palabrasDeLibro.removeAll(palabrasVacias);

        Set<String> palabrasUnicas = new TreeSet<>(palabrasDeLibro);

        for (String palabra : palabrasUnicas)
            System.out.println(palabra);

        System.out.println("\n Palabras sin Stopwords (Que no se repiten) = " + palabrasUnicas.size());

    }

    private static List<String> lineasDeArchivo(String nombreDeArchivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreDeArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea.toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineas;
    }

}
