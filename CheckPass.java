import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckPass {
    public static void main(String[] paramArrayOfString) {
        genpwd();
    }

    public static void genpwd(){
        String symbol = "!#$%^*_=+-/?<>)";
        for (int i = 0 ; i < symbol.length() ; i++){
            for (int z = 0; z < 100000; z++ ){
                String n = String.format("%05d", z);
                char f = n.charAt(0);
                char last = symbol.charAt(i);
                String full_pwd = n + f + last + "}";
                if (check(full_pwd) == true){
                    System.out.println("Password is: " + full_pwd);
                    System.exit(0);
                }
            }
        }
    }
    public static boolean check(String str1){
        if (str1.length() != 8) {
            return false;
        }
        if (!str1.substring(0, 6).matches("[0-9]+")) {
            return false;
        }
        if (!str1.substring(0, 1).equals(str1.substring(5, 6))) {
            return false;
        }
        if (!str1.endsWith("}")) {
            return false;
        }
        String str2 = "(?![@',&])\\p{Punct}";
        if (!str1.substring(6, 7).matches(str2)) {
            return false;
        }
        if (!getMd5(str1).equals("53e443c9f65cd5f816452ae66ec65834")) {
            return false;
        }
        return true;
    }

    public static String getMd5(String paramString) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] arrayOfByte = messageDigest.digest(paramString.getBytes());
            BigInteger bigInteger = new BigInteger(1, arrayOfByte);
            String str = bigInteger.toString(16);
            while (str.length() < 32)
                str = "0" + str;
            return str;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new RuntimeException(noSuchAlgorithmException);
        }
    }
}