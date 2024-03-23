package entidades;

public class DirectMemoryStorage {
    public static void createMainMemory(int TAG,int linesMM, int blockSizeBytes, String[][][] mainM) {
        for(int i = 0; i < TAG; i++){
            for(int j = 0; j < linesMM/TAG; j++){
                for(int k = 0; k < blockSizeBytes; k++){
                    mainM[i][j][k] = "0x" + Integer.toString((int)(Math.random()*10));
                }
            }
        }
    }
    public static void outputMainMemory(int TAG,int linesMM, int blockSizeBytes,String[][][] mainM){
        System.out.println("\nMEMORIA PRINCIPAL---------------------------------------------------------------");
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
        System.out.println("----------------------------------------------------------------------------------");
    }
    public static void createCacheMemory(int TAG, int LINES, int blockSizeBytes, String[][][] cacheM) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < LINES; j++) {
                for (int k = 0; k < blockSizeBytes; k++) {
                    cacheM[i][j][k] = "0x00";
                }
            }
        }
    }
    public static void outputCacheMemory(int TAG, int LINES, int blockSizeBytes, String[][][] cacheM) {
        System.out.println("\nMEMORIA CACHE---------------------------------------------------------------");
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < LINES; j++) {
                for (int k = 0; k < blockSizeBytes; k++) {
                    System.out.print(cacheM[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------");
    }
    
}
