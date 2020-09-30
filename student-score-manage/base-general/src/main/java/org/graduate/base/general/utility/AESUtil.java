package org.graduate.base.general.utility;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {

    public static String encrypt(String key, String src) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
        byte[] byteContent = src.getBytes(StandardCharsets.UTF_8);
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(key));// 初始化加密器
        byte[] result = cipher.doFinal(byteContent);
        return Base64.getEncoder().encodeToString(result);
    }

    public static String decrypt(String key, String src) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
        cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec(key));// 初始化解密器
        byte[] srcByte = Base64.getDecoder().decode(src);
        byte[] decrypted = cipher.doFinal(srcByte);
        System.out.println("原文：" + new String(decrypted));
        return new String(decrypted);
    }

    private static SecretKeySpec getSecretKeySpec(String key) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(key.getBytes());
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }
}
