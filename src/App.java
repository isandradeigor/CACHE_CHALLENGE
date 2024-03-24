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
            String addressInput = scanner.nextLine();
            String[] addressArray = addressInput.split(" ");

            // Handling Cache Hits and Misses
            ArrayList<String> hits = new ArrayList<>();
            ArrayList<String> misses = new ArrayList<>();
            int cacheHits = 0;
            int cacheMisses = 0;
            for (String addr : addressArray) {
                boolean hit = false;
                for (int i = 0; i < linesCACHE; i++) {
                    if (cacheM[0][i][0] != null && cacheM[0][i][0].equals(addr)) { // Check if address is in cache
                        cacheHits++;
                        hits.add(addr);
                        hit = true;
                        break;
                    }
                }
                if (!hit) { // Cache Miss
                    cacheMisses++;
                    misses.add(addr);
                    // Replace random cache line with new address
                    int randomIndex = new Random().nextInt(linesCACHE);
                    for (int i = 0; i < blockSizeBytes; i++) {
                        cacheM[0][randomIndex][i] = addr;
                    }
                }
            }
            // Output Cache Hits and Misses
            System.out.println("Cache Hits: " + cacheHits);
            System.out.println("Cache Hit Addresses: " + hits);
            System.out.println("Cache Misses: " + cacheMisses);
            System.out.println("Cache Miss Addresses: " + misses);
            
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
            String addressInput = scanner.nextLine();
            String[] addressArray = addressInput.split(" ");

            // Handling Cache Hits and Misses
            ArrayList<String> hits = new ArrayList<>();
            ArrayList<String> misses = new ArrayList<>();
            int cacheHits = 0;
            int cacheMisses = 0;
            for (String addr : addressArray) {
                boolean hit = false;
                for (int i = 0; i < linesCACHE; i++) {
                    if (cacheM[i][0] != null && cacheM[i][0].equals(addr)) { // Check if address is in cache
                        cacheHits++;
                        hits.add(addr);
                        hit = true;
                        break;
                    }
                }
                if (!hit) { // Cache Miss
                    cacheMisses++;
                    misses.add(addr);
                    // Replace random cache line with new address
                    int randomIndex = new Random().nextInt(linesCACHE);
                    for (int i = 0; i < blockSizeBytes; i++) {
                        cacheM[randomIndex][i] = addr;
                    }
                }
            }
            // Output Cache Hits and Misses
            System.out.println("Cache Hits: " + cacheHits);
            System.out.println("Cache Hit Addresses: " + hits);
            System.out.println("Cache Misses: " + cacheMisses);
            System.out.println("Cache Miss Addresses: " + misses);
            
        } // Invalid Mapping
        else {
            System.out.println("Invalid mapping type. Please choose 1 or 2.");
        }

        scanner.close();
    }
}
