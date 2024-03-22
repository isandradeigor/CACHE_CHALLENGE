import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Part 1 - Setting Up Memories
        System.out.println("Choose memory mapping type: ");
        System.out.println("1. Direct Mapping");
        System.out.println("2. Associative Mapping");
        int mappingType = scanner.nextInt();
        System.out.println("Choose a value for MM in bytes: ");
        int mainMemorySizeBytes = scanner.nextInt();
        System.out.println("Choose a value for CACHE in bytes: ");
        int cacheSizeBytes = scanner.nextInt();
        System.out.println("Choose a value for BlockSize in bytes: ");
        int blockSizeBytes = scanner.nextInt();

        // Output chosen mapping type
        if (mappingType == 1) {
            System.out.println("Chosen mapping: Direct Mapping");
        } else if (mappingType == 2) {
            System.out.println("Chosen mapping: Associative Mapping");
        }

        // Calculate parameters
        int TAG = mainMemorySizeBytes/cacheSizeBytes;
        int LINES = cacheSizeBytes/blockSizeBytes;
        int tagBits = (int) (Math.log(TAG) / Math.log(2));
        int lineBits = (int) (Math.log(LINES) / Math.log(2));
        int wordBits = (int) (Math.log(blockSizeBytes) / Math.log(2));

        System.out.println("Number of bits used in TAG: " + tagBits);
        if (mappingType == 1) {
            System.out.println("Number of bits used in LINE: " + lineBits);
        } else {
            LINES = 0;
            System.out.println("Number of bits used in LINE: " + LINES);
        }
        System.out.println("Number of bits used in WORD: " + wordBits);

        // Part 2 - Cache Hit and Cache Miss---------------------------------------------------------------------------------------
        //System.out.println("Enter sequence of addresses in hexadecimal (separated by space): ");
        
        // Initialize cache
        int[] tagBit = new int[(int) Math.pow(2, tagBits)];
        int[] lineBit = new int[(int) Math.pow(2, lineBits)];
        amountOfBits(tagBit);//verifies the amount of tagbits
        amountOfBits(lineBit);//verifies the amount of tagbits
        
        
        scanner.close();
    }

    public static void amountOfBits(int[] argument) {
        for(int i = 0; i < argument.length; i++){
            argument[i] = i;
        }
        for(int i = 0; i < argument.length; i++){
            System.out.print(argument[i] + ", "); 
        }
        System.out.println();
    }
}
