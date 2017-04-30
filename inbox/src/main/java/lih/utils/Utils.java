package lih.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by huanli on 29/04/2017.
 */
@Component
public class Utils {


    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    private final String SHA1 = "SHA1";

    public String sha1Password(String pwd, String salt) {

        try {

            MessageDigest messageDigest = MessageDigest.getInstance(SHA1);
            messageDigest.update((pwd + salt).getBytes());

            return bytesToHexString(messageDigest.digest());

        } catch (NoSuchAlgorithmException e) {

            LOGGER.warn("get sha1 method error, {}", e);
            return null;
        }

    }

    public String generateSalt() {

        byte[] b = new byte[8];
        Random random = new Random();
        random.nextBytes(b);

        return new String(Base64.encodeBase64(b));
    }

    private String bytesToHexString(byte[] str) {
        if (StringUtils.isEmpty(str)) {

            LOGGER.warn("byte to HexString with null input error");
            return null;
        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length; ++i) {
            String hv = Integer.toHexString(str[i] & 0xFF);
            sb.append(hv.length() < 2 ? 0 + hv : hv);
        }

        return sb.toString();
    }

}
