import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Pais {
    private String nome;
    private double tvAudiencia;

    public Pais(String nome, double tvAudiencia) {
        this.nome = nome;
        this.tvAudiencia = tvAudiencia;
    }

    public String getNome() {
        return nome;
    }

    public double getTvAudiencia() {
        return tvAudiencia;
    }
}

public class Fifa {
    public static void main(String[] args) {
        String csvFile = "fifa_countries_audience.csv";
        List<Pais> paises = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String linha;
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0];
                double tvAudiencia = Double.parseDouble(dados[3]);
                paises.add(new Pais(nome, tvAudiencia));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nDigite a opção desejada:");
            System.out.println("[1] Ordenar por Ordem Alfabética");
            System.out.println("[2] Ordenar por audiência TV");
            System.out.println("[3] Encerrar");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    ordenarAlfabeticamente(paises);
                    break;
                case 2:
                    ordenarPorAudiencia(paises);
                    break;
                case 3:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static void ordenarAlfabeticamente(List<Pais> paises) {
        Collections.sort(paises, Comparator.comparing(Pais::getNome));
        printPaises(paises);
    }

    private static void ordenarPorAudiencia(List<Pais> paises) {
        Collections.sort(paises, Comparator.comparingDouble(Pais::getTvAudiencia).reversed());
        printPaises(paises);
    }

    private static void printPaises(List<Pais> paises) {
        for (Pais pais : paises) {
            System.out.printf("País: %s, Audiência: %.1f%%\n", pais.getNome(), pais.getTvAudiencia());
        }
    }
}