import java.util.*;

import entidades.CalculateBits;
import entidades.DirectMemoryStorage;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Part 1 - Setting Up Memories
        System.out.println("Choose memory mapping type: ");
        System.out.println("1. Direct Mapping or 2. Associative Mapping");
        int mappingType = scanner.nextInt();
        System.out.println("Choose a value for MM in bytes: ");
        int mainMemorySizeBytes = scanner.nextInt();
        System.out.println("Choose a value for CACHE in bytes: ");
        int cacheSizeBytes = scanner.nextInt();
        System.out.println("Choose a value for BlockSize in bytes: ");
        int blockSizeBytes = scanner.nextInt();
        //CALCULATE TAG, LINES, WORDS
        int linesMM = mainMemorySizeBytes / blockSizeBytes;
        int linesCACHE = cacheSizeBytes / blockSizeBytes;
        // Output calculated values by Mapping Type - Parte 1
        if (mappingType == 1) {
            System.out.println("Chosen mapping: Direct Mapping");
            int TAG = mainMemorySizeBytes / cacheSizeBytes;
            CalculateBits.calculateBitsDirectMapping(TAG, linesCACHE, blockSizeBytes);
            //MEMORIA PRINCIPAL--------------------------------------------------------------
            String[][][] mainM = new String[TAG][linesMM/blockSizeBytes][blockSizeBytes];
            DirectMemoryStorage.createMainMemory(TAG,linesMM,blockSizeBytes,mainM);
            DirectMemoryStorage.outputMainMemory(TAG,linesMM,blockSizeBytes,mainM);

            //MEMORIA CACHE------------------------------------------------------------------
            String[][][] cacheM = new String[1][linesCACHE][blockSizeBytes];
            DirectMemoryStorage.createCacheMemory(TAG,linesCACHE,blockSizeBytes,cacheM);
            DirectMemoryStorage.outputCacheMemory(TAG,linesCACHE,blockSizeBytes,cacheM);
        } else if (mappingType == 2) {
            int TAG = linesMM;
            System.out.println("Chosen mapping: Associative Mapping");
            CalculateBits.calculateBitsAssociativeMapping(TAG, blockSizeBytes);
        }else{
            System.out.println("Invalid mapping type. Please choose 1 or 2.");
        }

        scanner.close();
    }

}
