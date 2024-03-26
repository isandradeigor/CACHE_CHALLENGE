import java.util.*;
import entidades.CalculateBits;
import entidades.DirectMemoryStorage;
import entidades.AssociativeMemoryStorage;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Setting Up Memories
        System.out.println("Choose memory mapping type: ");
        System.out.println("1. Direct Mapping or 2. Associative Mapping");
        int mappingType = scanner.nextInt();
        System.out.println("Choose a value for MM in bytes: ");
        int mainMemorySizeBytes = scanner.nextInt();
        System.out.println("Choose a value for CACHE in bytes: ");
        int cacheSizeBytes = scanner.nextInt();
        System.out.println("Choose a value for BlockSize in bytes: ");
        int blockSizeBytes = scanner.nextInt();
        scanner.nextLine();
        // Calculating the number of lines
        int linesMM = mainMemorySizeBytes / blockSizeBytes;
        int linesCACHE = cacheSizeBytes / blockSizeBytes;
        // Direct Mapping
        if (mappingType == 1) {
            System.out.println("Chosen mapping: Direct Mapping");
            int TAG = mainMemorySizeBytes / cacheSizeBytes;
            CalculateBits.calculateBitsDirectMapping(TAG, linesCACHE, blockSizeBytes);
            // MAIN MEMORY--------------------------------------------------------------
            String[][][] mainM = new String[TAG][linesMM / TAG][blockSizeBytes];
            DirectMemoryStorage.createMainMemory(TAG, linesMM, blockSizeBytes, mainM);

            DirectMemoryStorage.outputMainMemory(TAG, linesMM, blockSizeBytes, mainM);
            // CACHE MEMORY-------------------------------------------------------------
            String[][][] cacheM = new String[1][linesCACHE][blockSizeBytes];
            DirectMemoryStorage.createCacheMemory(TAG, linesCACHE, blockSizeBytes, cacheM);
            DirectMemoryStorage.outputCacheMemory(TAG, linesCACHE, blockSizeBytes, cacheM);
            // Looking for addresses
            System.out.println("Enter addresses separated by space: ");
            String[] inputValues = scanner.nextLine().split(" ");
            int cacheHit = searchCacheAndRam(TAG, linesMM, blockSizeBytes, mainM, cacheM, linesCACHE, inputValues);
            System.out.println("Número total de hits na cache: " + cacheHit);
            System.out.println("Número total de misses na cache: " + (inputValues.length - cacheHit));
        } // Associative Mapping
        else if (mappingType == 2) {
            int TAG = linesMM;
            System.out.println("Chosen mapping: Associative Mapping");
            CalculateBits.calculateBitsAssociativeMapping(TAG, blockSizeBytes);
            // MAIN MEMORY--------------------------------------------------------------
            String[][] mainM = new String[TAG][blockSizeBytes];
            AssociativeMemoryStorage.createMainMemory(TAG, linesMM, blockSizeBytes, mainM);
            AssociativeMemoryStorage.outputMainMemory(TAG, linesMM, blockSizeBytes, mainM);
            // CACHE MEMORY-------------------------------------------------------------
            String[][] cacheM = new String[linesCACHE][blockSizeBytes];
            AssociativeMemoryStorage.createCacheMemory(TAG, linesCACHE, blockSizeBytes, cacheM);
            AssociativeMemoryStorage.outputCacheMemory(TAG, linesCACHE, blockSizeBytes, cacheM);
            // Looking for addresses
            System.out.println("Enter addresses separated by space: ");
            String[] inputValues = scanner.nextLine().split(" ");
            
            // Executando a função para buscar valores na cache e na RAM
            int cacheHit = searchCacheAndRamAssociative(TAG, linesMM, blockSizeBytes, mainM, cacheM, linesCACHE, inputValues);
            
            // Exibindo o número total de hits e misses na cache
            System.out.println("Número total de hits na cache: " + cacheHit);
            System.out.println("Número total de misses na cache: " + (inputValues.length - cacheHit));
        } // Invalid Mapping
        else {
            System.out.println("Invalid mapping type. Please choose 1 or 2.");
        }
        scanner.close();
    }

    public static int searchCacheAndRam(int TAG, int linesMM, int blockSizeBytes, String[][][] mainM,
        String[][][] cacheM,
        int LINES, String[] inputValues) {
        int cacheHit = 0;
        for (String value : inputValues) {
            boolean foundInCache = false;
            // Verificando se o valor está na cache
            for (int j = 0; j < LINES; j++) {
                for (int k = 0; k < blockSizeBytes; k++) {
                    if (cacheM[0][j][k].equals(value)) {
                        foundInCache = true;
                        cacheHit++;
                        break;
                    }
                }
                if (foundInCache) {
                    break;
                }
            }

            if (!foundInCache) {
                // Searching in MAIN MEMORY (RAM)
                boolean foundInMainMemory = false;
                for (int i = 0; i < TAG; i++) {
                    for (int j = 0; j < linesMM / TAG; j++) {
                        for (int k = 0; k < blockSizeBytes; k++) {
                            if (mainM[i][j][k].equals(value)) {
                                foundInMainMemory = true;
                                // Copiando a linha inteira da memória principal para a cache
                                for (int l = 0; l < LINES; l++) {
                                    for (int m = 0; m < blockSizeBytes; m++) {
                                        cacheM[0][l][m] = mainM[i][j][m];
                                    }
                                }
                                break;
                            }
                        }
                        if (foundInMainMemory) {
                            break;
                        }
                    }
                    if (foundInMainMemory) {
                        break;
                    }
                }
            }
        }
        return cacheHit;
    }
    public static int searchCacheAndRamAssociative(int TAG, int linesMM, int blockSizeBytes, String[][] mainM, String[][] cacheM,
            int linesCACHE, String[] inputValues) {
        int cacheHit = 0;

        for (String value : inputValues) {
            boolean foundInCache = false;

            // Verificando se o valor está na cache
            for (int j = 0; j < linesCACHE; j++) {
                for (int k = 0; k < blockSizeBytes; k++) {
                    if (cacheM[j][k].equals(value)) {
                        foundInCache = true;
                        cacheHit++;
                        break;
                    }
                }
                if (foundInCache) {
                    break;
                }
            }

            if (!foundInCache) {
                // Procurando na memória principal (RAM)
                boolean foundInMainMemory = false;
                for (int i = 0; i < TAG; i++) {
                    for (int k = 0; k < blockSizeBytes; k++) {
                        if (mainM[i][k].equals(value)) {
                            foundInMainMemory = true;
                            // Copiando a linha inteira da memória principal para a cache
                            for (int l = 0; l < linesCACHE; l++) {
                                for (int m = 0; m < blockSizeBytes; m++) {
                                    cacheM[l][m] = mainM[i][m];
                                }
                            }
                            break;
                        }
                    }
                    if (foundInMainMemory) {
                        break;
                    }
                }
            }
        }
        return cacheHit;
    }
}
