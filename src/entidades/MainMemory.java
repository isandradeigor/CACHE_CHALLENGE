package entidades;

public class MainMemory {
    public static void createMainMemory(int TAG,int linesMM, int blockSizeBytes, String[][][] mainM) {
        for(int i = 0; i < TAG; i++){
            for(int j = 0; j < linesMM/TAG; j++){
                for(int k = 0; k < blockSizeBytes; k++){
                    mainM[i][j][k] = "0x" + Integer.toHexString((int)(Math.random()*1000));
                }
            }
        }
    }
    public static void outputMainMemory(int TAG,int linesMM, int blockSizeBytes,String[][][] mainM){
        System.out.println("MEMORIA PRINCIPAL---------------------------------------------------------------");
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
}
