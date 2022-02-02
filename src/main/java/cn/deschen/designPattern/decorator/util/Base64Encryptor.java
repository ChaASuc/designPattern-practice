package cn.deschen.designPattern.decorator.util;

import java.util.Base64;

/**
 * @Author hanbin_chen
 * @Description Base64加解密工具
 * @Version V1.0.0
 */
public class Base64Encryptor {

    public static String encrypt(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static String decrypt(String encryptedData) {
        return new String(Base64.getDecoder().decode(encryptedData));
    }

}
