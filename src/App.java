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
        int indexBits = (int) (Math.log(LINES) / Math.log(2));
        int blockOffsetBits = (int) (Math.log(blockSizeBytes) / Math.log(2));

        System.out.println("Number of bits used in TAG: " + tagBits);
        if (mappingType == 1) {
            System.out.println("Number of bits used in LINE: " + indexBits);
        } else {
            LINES = 0;
            System.out.println("Number of bits used in LINE: " + LINES);
        }
        System.out.println("Number of bits used in WORD: " + blockOffsetBits);
        // Part 2 - Cache Hit and Cache Miss
        System.out.println("Enter sequence of addresses in hexadecimal (separated by space): ");
        scanner.nextLine(); // consume newline

        scanner.close();
    }
}
