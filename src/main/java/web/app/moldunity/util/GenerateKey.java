package web.app.moldunity.util;

import java.util.Random;

public final class GenerateKey {
    private final static byte LENGTH = 16;
    private final static String s = "A1aB2bC3cD4dE5eF6fG7gH8hI9iJ0jKLMNOPQRSTUVWXYZlmnopqrstuvwxyz";
    private final static String shex = "0123456789ABCDEF";
    private final static Random r = new Random();

    private GenerateKey(){};

    public static String generate(){
        StringBuilder out = new StringBuilder(LENGTH);
        for(int i = 0; i < LENGTH; i++){
            int result = r.nextInt(s.length()-1);
            out.append(s.charAt(result));
        }
        return out.toString();
    }

    public static String generateHex(){
        StringBuilder out = new StringBuilder(LENGTH);
        for(int i = 0; i < LENGTH; i++){
            int result = r.nextInt(shex.length()-1);
            out.append(shex.charAt(result));
        }
        return out.toString();
    }
}










