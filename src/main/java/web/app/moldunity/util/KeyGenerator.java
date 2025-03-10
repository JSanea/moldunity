package web.app.moldunity.util;

import java.util.Random;

public final class KeyGenerator {
    private static Integer LENGTH;
    private final static Integer UPPER_RANGE = 256;
    private final static Integer LOWER_RANGE = 16;
    private final static String s = "A1aB2bC3cD4dE5eF6fG7gH8hI9iJ0jKLMNOPQRSTUVWXYZlmnopqrstuvwxyz";
    private final static Random r = new Random();

    private KeyGenerator(){};

    public static String generate(Integer len){

        if(len > UPPER_RANGE){
            LENGTH = UPPER_RANGE;
        } else if (len < LOWER_RANGE) {
            LENGTH = LOWER_RANGE;
        } else {
            LENGTH = len;
        }

        StringBuilder out = new StringBuilder(LENGTH);
        for(int i = 0; i < LENGTH; i++){
            int result = r.nextInt(s.length()-1);
            out.append(s.charAt(result));
        }
        return out.toString();
    }
}










