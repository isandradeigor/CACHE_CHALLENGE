import java.util.*;

import entidades.CalculateBits;
import entidades.MainMemory;
import entidades.CacheMemory;

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
        int TAG = mainMemorySizeBytes / cacheSizeBytes;
        int LINES = cacheSizeBytes / blockSizeBytes;
        // Output calculated values by Mapping Type - Parte 1
        if (mappingType == 1) {
            System.out.println("Chosen mapping: Direct Mapping");
            CalculateBits.calculateBitsDirectMapping(TAG, LINES, blockSizeBytes);
        } else if (mappingType == 2) {
            System.out.println("Chosen mapping: Associative Mapping");
            CalculateBits.calculateBitsAssociativeMapping(TAG, LINES, blockSizeBytes);
        }

        //MEMORIA PRINCIPAL--------------------------------------------------------------
        //int linesMM = mainMemorySizeBytes / blockSizeBytes;
        //String[][][] mainM = new String[TAG][linesMM/blockSizeBytes][blockSizeBytes];
        //MainMemory.createMainMemory(TAG,linesMM,blockSizeBytes,mainM);
        //MainMemory.outputMainMemory(TAG,linesMM,blockSizeBytes,mainM);

        //MEMORIA CACHE------------------------------------------------------------------
        //String[][][] cacheM = new String[TAG][LINES][blockSizeBytes];
        
        scanner.close();
    }

}
