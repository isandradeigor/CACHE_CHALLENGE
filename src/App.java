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
            String[][][] mainM = new String[TAG][linesMM / blockSizeBytes][blockSizeBytes];
            DirectMemoryStorage.createMainMemory(TAG, linesMM, blockSizeBytes, mainM);
            DirectMemoryStorage.outputMainMemory(TAG, linesMM, blockSizeBytes, mainM);
            // CACHE MEMORY-------------------------------------------------------------
            String[][][] cacheM = new String[1][linesCACHE][blockSizeBytes];
            DirectMemoryStorage.createCacheMemory(TAG, linesCACHE, blockSizeBytes, cacheM);
            DirectMemoryStorage.outputCacheMemory(TAG, linesCACHE, blockSizeBytes, cacheM);
            // Looking fro address
            System.out.println("Enter an addresses: ");
            String address = scanner.nextLine();
            String[] addressArray = address.split(" ");
            
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
            System.out.println("Enter an addresses: ");
            String address = scanner.nextLine();
            String[] addressArray = address.split(" ");
            
        } // Invalid Mapping
        else {
            System.out.println("Invalid mapping type. Please choose 1 or 2.");
        }

        scanner.close();
    }
}
