package entidades;

public class CalculateBits {
    public static void calculateBitsDirectMapping(int TAG, int LINES, int blockSizeBytes) {
        int tagBits = (int) (Math.log(TAG) / Math.log(2)); // tag in bits
        int lineBits = (int) (Math.log(LINES) / Math.log(2));// line in bits
        int wordBits = (int) (Math.log(blockSizeBytes) / Math.log(2)); // word in bits
        System.out.println("TAG: " + tagBits + " bits");
        System.out.println("LINES: " + lineBits + " bits");
        System.out.println("WORD: " + wordBits + " bits");
    }
    public static void calculateBitsAssociativeMapping(int TAG, int blockSizeBytes) {
        int tagBits = (int) (Math.log(TAG) / Math.log(2)); // tag in bits
        int wordBits = (int) (Math.log(blockSizeBytes) / Math.log(2)); // word in bits
        System.out.println("TAG: " + tagBits + " bits");
        System.out.println("WORD: " + wordBits + " bits");
    }
    public static void convertAddressToBitsDirect(String[] inputValues, String[][][] mainM, String[][][] cacheM, int blockSizeBytes) {
        int cacheHitCount = 0;
        int cacheMissCount = 0;
        for (int i = 0; i < inputValues.length; i++) {
            // Access each element in the inputValues array here
            inputValues[i] = inputValues[i].replace("0x", "");
            inputValues[i] = Integer.toBinaryString(Integer.parseInt(inputValues[i], 16));
            System.out.println(inputValues[i]);
            int tagAddress = 0;
            int lineAddress = 0;
            int wordAddress = 0;
            tagAddress = Integer.parseInt(inputValues[i].substring(0, 2), 2);
            lineAddress = Integer.parseInt(inputValues[i].substring(2, 4), 2);
            wordAddress = Integer.parseInt(inputValues[i].substring(4), 2);
            System.out.println("TAG: " + tagAddress + " LINES: " + lineAddress + " WORD: " + wordAddress);
            System.out.println("MAIN MEMORY ADDRESS: " + mainM[tagAddress][lineAddress][wordAddress]);
            System.out.println("CACHE MEMORY ADDRESS: " + mainM[tagAddress][lineAddress][wordAddress]);
            boolean cacheHit = false;
            for(int k = 0; k < blockSizeBytes - 1; k++){
                for(int j = 0; j < blockSizeBytes - 1; j++){
                    if(cacheM[0][k][j] == mainM[tagAddress][k][j]){
                        cacheHit = true;
                        cacheHitCount++;
                        break;
                    }
                }
            }
            if(cacheHit == false){
                for(int j = 0; j < blockSizeBytes; j++){
                    cacheM[0][lineAddress][j] = mainM[tagAddress][lineAddress][j];
                }
                cacheMissCount++;
            }
        }
        System.out.println("-----------------------------");
        System.out.println("CACHE HITS: " + cacheHitCount);
        System.out.println("CACHE MISSES: " + cacheMissCount);
    }
    public static void convertAddressToBitsAssociative(String[] inputValues, String[][] mainM, String[][] cacheM,int blockSizeBytes) {
        int cacheHitCount = 0;
        int cacheMissCount = 0;
        for (int i = 0; i < inputValues.length; i++) {
            // Access each element in the inputValues array here
            inputValues[i] = inputValues[i].replace("0x", "");
            inputValues[i] = Integer.toBinaryString(Integer.parseInt(inputValues[i], 16));
            System.out.println(inputValues[i]);
            int tagAddress = 0;
            int wordAddress = 0;
            wordAddress = Integer.parseInt(inputValues[i].substring(inputValues[i].length() - 2), 2);
            tagAddress = Integer.parseInt(inputValues[i].substring(0, inputValues[i].length() - 2), 2);
            System.out.println("TAG: " + tagAddress + " WORD: " + wordAddress);
            System.out.println("MAIN MEMORY ADDRESS: " + mainM[tagAddress][wordAddress]);
            System.out.println("CACHE MEMORY ADDRESS: " + mainM[tagAddress][wordAddress]);
            boolean cacheHit = false;
            for(int j = 0; j < blockSizeBytes - 1; j++){
                if(cacheM[j][wordAddress] == mainM[tagAddress][wordAddress]){
                    cacheHit = true;
                    cacheHitCount++;
                }
            }
            if(cacheHit == false){
                int randomTag = (int) Math.round(Math.random() * (blockSizeBytes - 1));
                for(int j = 0; j < blockSizeBytes; j++){
                    cacheM[randomTag][j] = mainM[tagAddress][j];
                }
                cacheMissCount++;
            }
        }
        System.out.println("-----------------------------");
        System.out.println("CACHE HITS: " + cacheHitCount);
        System.out.println("CACHE MISSES: " + cacheMissCount);
    }
}
