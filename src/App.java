import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*// Part 1 - Setting Up Memories
        System.out.println("Choose memory mapping type: ");
        System.out.println("1. Direct Mapping");
        System.out.println("2. Associative Mapping");
        int mappingType = scanner.nextInt();*/
        System.out.println("Choose a value for MM in bytes: ");
        int mainMemorySizeBytes = scanner.nextInt();
        System.out.println("Choose a value for CACHE in bytes: ");
        int cacheSizeBytes = scanner.nextInt();
        System.out.println("Choose a value for BlockSize in bytes: ");
        int blockSizeBytes = scanner.nextInt();

        // Output chosen mapping type
        /*if (mappingType == 1) {
            System.out.println("Chosen mapping: Direct Mapping");
        } else if (mappingType == 2) {
            System.out.println("Chosen mapping: Associative Mapping");
        }*/

        // Calculate parameters
        //memória cache:
        int TAG = mainMemorySizeBytes / cacheSizeBytes;
        //int LINES = cacheSizeBytes / blockSizeBytes;
        //MEMORIA PRINCIPAL---------------------------------------------------------------
        int linesMM = mainMemorySizeBytes / blockSizeBytes;
        String[][][] mainM = new String[TAG][linesMM/blockSizeBytes][blockSizeBytes];
        for(int i = 0; i < TAG; i++){
            for(int j = 0; j < linesMM/TAG; j++){
                for(int k = 0; k < blockSizeBytes; k++){
                    mainM[i][j][k] = "0x" + Integer.toHexString((int)(Math.random()*1000));
                }
            }
        }
        for(int i = 0; i < TAG; i++){
            System.out.println("TAG: " + i);
            for(int j = 0; j < linesMM/TAG; j++){
                for(int k = 0; k < blockSizeBytes; k++){
                    System.out.print(mainM[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        //----------------------------------------------------------------------

        /*int tagBits = (int) (Math.log(TAG) / Math.log(2)); //tag in bits
        int lineBits = (int) (Math.log(LINES) / Math.log(2));// line in bits
        int wordBits = (int) (Math.log(blockSizeBytes) / Math.log(2)); //word in bits

        System.out.println("Number of bits used in TAG: " + tagBits);
        if (mappingType == 1) {
            System.out.println("Number of bits used in LINE: " + lineBits);
        } else {
            LINES = 0;
            System.out.println("Number of bits used in LINE: " + LINES);
        }
        System.out.println("Number of bits used in WORD: " + wordBits);*/

        // Part 2 - Cache Hit and Cache
        // Miss---------------------------------------------------------------------------------------
        // System.out.println("Enter sequence of addresses in hexadecimal (separated by
        // space): ");

        // TRANSFORM 
        //int tagBit = (int) Math.pow(2, tagBits); // number of tags
        //int lineBit = (int) Math.pow(2, lineBits); // number of lines

        scanner.close();
    }

}
