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
        int TAG = mainMemorySizeBytes / cacheSizeBytes;
        int LINES = cacheSizeBytes / blockSizeBytes;
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

        // Part 2 - Cache Hit and Cache
        // Miss---------------------------------------------------------------------------------------
        // System.out.println("Enter sequence of addresses in hexadecimal (separated by
        // space): ");

        // Initialize cache
        int tagBitting = (int) Math.pow(2, tagBits);
        int[][] tagBit = new int[tagBitting][];
        int lineBit = (int) Math.pow(2, lineBits);

        // Calculando o número de linhas para cada entrada em tagBit
        int linesPerTag = lineBit / tagBitting;

        // Inicializar tagBit e mainM dentro de tagBit
        for (int i = 0; i < tagBitting; i++) {
            tagBit[i] = new int[linesPerTag * blockSizeBytes];
            representation(tagBit[i], linesPerTag, blockSizeBytes);
        }
        for (int i = 0; i < tagBit.length; i++) {
            System.out.println("Tag " + i + ":");
            for (int j = 0; j < tagBit[i].length; j++) {
                System.out.print(tagBit[i][j] + " ");
            }
            System.out.println("\n--------------------");
        }
        scanner.close();
    }

    public static void representation(int[] argument, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                argument[i * cols + j] = (int) Math.round(Math.random() * 10);
            }
        }
    }
}
