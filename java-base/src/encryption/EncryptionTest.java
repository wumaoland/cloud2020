package encryption;

import cn.hutool.crypto.SecureUtil;

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
        System.out.println(new BigInteger(1, digest).toString(16));
        //aes对称加密
        System.out.println(SecureUtil.aes("你好你好你a".getBytes("UTF-8")).encryptHex("fdsfds"));
        System.out.println(SecureUtil.aes("你好你好你a".getBytes("UTF-8")).decryptStr("06eeee0a9d0c5c9062b3eb9e3c759e0c"));
    }

}
