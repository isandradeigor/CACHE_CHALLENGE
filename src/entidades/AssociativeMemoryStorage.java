package entidades;

import java.util.ArrayList;
import java.util.Collections;

public class AssociativeMemoryStorage {
    public static void createMainMemory(int TAG,int linesMM, int blockSizeBytes, String[][] mainM) {
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
        for(int j = 0; j < TAG; j++){
            for(int k = 0; k < blockSizeBytes; k++){
                mainM[j][k] = "0x" + Integer.toHexString(allNumbers.get(index++));
            }
        }
    }
    public static void outputMainMemory(int TAG,int linesMM, int blockSizeBytes,String[][] mainM){
        System.out.println("\nMEMORIA PRINCIPAL---------------------------------------------------------------\n");
        for(int j = 0; j < TAG; j++){
            System.out.print("TAG: " + j + " [ ");
            for(int k = 0; k < blockSizeBytes; k++){
                System.out.print(mainM[j][k] + " ");
            }
            System.out.print("]");
            System.out.println();
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------");
    }
    public static void createCacheMemory(int TAG, int LINES, int blockSizeBytes, String[][] cacheM) {
        for (int j = 0; j < LINES; j++) {
            for (int k = 0; k < blockSizeBytes; k++) {
                cacheM[j][k] = "0x00";
            }
        }
    }
    public static void outputCacheMemory(int TAG, int LINES, int blockSizeBytes, String[][] cacheM) {
        System.out.println("\nMEMORIA CACHE---------------------------------------------------------------");
        for (int j = 0; j < LINES; j++) {
            for (int k = 0; k < blockSizeBytes; k++) {
                System.out.print(cacheM[j][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------");
    }
}
