package entidades;

import java.util.ArrayList;
import java.util.Collections;

public class DirectMemoryStorage {
    public static void createMainMemory(int TAG, int linesMM, int blockSizeBytes, String[][][] mainM) {
        // Calcular o total de células na memória principal
        int totalCells = TAG * (linesMM / TAG) * blockSizeBytes;

        // Criar uma lista para armazenar todos os números possíveis
        ArrayList<Integer> allNumbers = new ArrayList<>();
        for (int i = 0; i < totalCells; i++) {
            allNumbers.add(i);
        }

        // Embaralhar a lista para garantir que os números estejam em ordem aleatória
        Collections.shuffle(allNumbers);

        // Atribuir os valores da lista à memória principal
        int index = 0;
        for (int i = 0; i < TAG; i++) {
            for (int j = 0; j < linesMM / TAG; j++) {
                for (int k = 0; k < blockSizeBytes; k++) {
                    // Converter o número para hexadecimal e atribuir à memória principal
                    mainM[i][j][k] = "0x" + Integer.toHexString(allNumbers.get(index++));
                }
            }
        }
    }

    public static void outputMainMemory(int TAG, int linesMM, int blockSizeBytes, String[][][] mainM) {
        System.out.println("\nMEMORIA PRINCIPAL---------------------------------------------------------------");
        for (int i = 0; i < TAG; i++) {
            System.out.println("TAG: " + i);
            for (int j = 0; j < linesMM / TAG; j++) {
                for (int k = 0; k < blockSizeBytes; k++) {
                    System.out.print(mainM[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------");
    }

    public static void createCacheMemory(int TAG, int LINES, int blockSizeBytes, String[][][] cacheM) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < LINES; j++) {
                for (int k = 0; k < blockSizeBytes; k++) {
                    cacheM[i][j][k] = "0x00";
                }
            }
        }
    }

    public static void outputCacheMemory(int TAG, int LINES, int blockSizeBytes, String[][][] cacheM) {
        System.out.println("\nMEMORIA CACHE---------------------------------------------------------------");
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < LINES; j++) {
                for (int k = 0; k < blockSizeBytes; k++) {
                    System.out.print(cacheM[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------");
    }
}
