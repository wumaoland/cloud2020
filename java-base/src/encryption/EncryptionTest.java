package encryption;

import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //jdk自带算法MD5
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update("123456".getBytes("UTF-8"));
        byte[] digest = md5.digest();
        System.out.println(new BigInteger(1,digest).toString(16));


    }

}
