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
    public static void convertAddressToBitsDirect(String[] inputValues, String[][][] mainM) {
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
        }
    }
    public static void convertAddressToBitsAssociative(String[] inputValues, String[][] mainM) {
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
        }
    }
}
