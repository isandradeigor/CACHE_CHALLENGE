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
            // Convert addresses to bits
            int tagAddress = 0;
            int lineAddress = 0;
            int wordAddress = 0;
            CalculateBits.convertAddressToBits(inputValues, tagAddress, lineAddress, wordAddress);
            // Checking if the address is in the cache
            boolean cacheHit = false;
            for (int i = 0; i < linesCACHE; i++) {
                if (cacheM[0][i][0] != null && cacheM[0][i][0].equals(mainM[tagAddress][lineAddress][0])) {
                    cacheHit = true;
                    break;
                }
            }

            if (cacheHit) {
                System.out.println("Cache hit!");
            } else {
                System.out.println("Cache miss!");
                // Fetching the block from main memory to cache
                cacheM[0][lineAddress] = mainM[tagAddress][lineAddress];
            }

            // Printing the data at the specified address
            System.out.println("Data at address " + inputValues[0] + ": " + mainM[tagAddress][lineAddress][wordAddress]);
            System.out.println("----------------------------------------------------------------------------------");
            //System.out.println(mainM[tagAddress][lineAddress][wordAddress]);
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

        } // Invalid Mapping
        else {
            System.out.println("Invalid mapping type. Please choose 1 or 2.");
        }
        scanner.close();
    }
}
