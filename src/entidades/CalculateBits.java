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
    public static void calculateBitsAssociativeMapping(int TAG, int LINES, int blockSizeBytes) {
        int tagBits = (int) (Math.log(TAG) / Math.log(2)); // tag in bits
        int lineBits = (int) (Math.log(LINES) / Math.log(2));// line in bits
        int wordBits = (int) (Math.log(blockSizeBytes) / Math.log(2)); // word in bits
        System.out.println("TAG: " + tagBits + " bits");
        System.out.println("LINES: " + lineBits + " bits");
        System.out.println("WORD: " + wordBits + " bits");
    }
}
