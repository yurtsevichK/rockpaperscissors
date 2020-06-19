package hmac;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;

public class Hmac {

    private final static String HMAC_SHA_2_256 = "HmacSHA256";
    private final static int SIZE_KEY = 16;
    private String key;

    public String createHmac(String data) {
       return calculateHMAC(data);
    }

    public String getKey() {
        return key;
    }

    private void initRandomKey() {
        SecureRandom random = new SecureRandom();
        byte[] seed = random.generateSeed(SIZE_KEY);

        Formatter formatter = new Formatter();
        for (byte b : seed) {
            formatter.format("%02X", b);
        }
        key = formatter.toString();
    }

    private String calculateHMAC(String data) {
        initRandomKey();
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), HMAC_SHA_2_256);
        Mac mac = null;
        try {
            mac = Mac.getInstance(HMAC_SHA_2_256);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            if (mac != null) {
                mac.init(secretKey);
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        if (mac != null) {
            return toHexString(mac.doFinal(data.getBytes()));
        }
        return null;
    }

    private String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
