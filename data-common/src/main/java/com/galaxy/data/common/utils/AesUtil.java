package com.galaxy.data.common.utils;

import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

/**
 * AES加密解密
 */
public class AesUtil {

    private static Logger logger = LoggerFactory.getLogger(AesUtil.class);

    private static final String ENCODING = "UTF-8";

    /**
     * AES加密
     *
     * @param content 明文
     * @param key     加密秘钥
     * @return 密文
     * @throws Exception
     */
    public static String encryptAes(String content, String key) {
        if (StringUtils.isEmpty(content)) {
            logger.error("明文不能为空!");
            return null;
        }
        try {
            // AES加密
            byte[] encryptResult = encrypt(content, key);
            // BASE64位加密
            return ebotongEncrypto(encryptResult);
        } catch (Exception e) {
            logger.error("AES加密异常", e);
            return null;
        }
    }

    /**
     * AES解密
     *
     * @param encryptResultStr 密文
     * @return 明文
     */
    public static String decryptAes(String encryptResultStr, String key) {
        if (StringUtils.isEmpty(encryptResultStr)) {
            logger.error("密文不能为空!");
            return null;
        }
        try {
            // BASE64位解密
            byte[] decrpt = ebotongDecrypto(encryptResultStr);
            // AES解密
            return new String(decrypt(decrpt, key));
        } catch (Exception e) { // 当密文不规范时会报错，可忽略，但调用的地方需要考虑
            logger.error("AES解密异常", e);
            return null;
        }
    }

    /**
     * base64加密字符串
     */
    private static String ebotongEncrypto(byte[] str) {
        BASE64Encoder base64encoder = new BASE64Encoder();
        return base64encoder.encodeBuffer(str);
    }

    /**
     * base64解密字符串
     */
    private static byte[] ebotongDecrypto(String str) {
        BASE64Decoder base64decoder = new BASE64Decoder();
        try {
            return base64decoder.decodeBuffer(str);
        } catch (IOException e) {
            logger.error("IO 异常", e);
            return str.getBytes();
        }
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     * @Description： Aes加密流程：
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    private static byte[] encrypt(String content, String password) {
        try {
			/*//[1].利用KeyGenerator构造密钥生成器，指定为AES算法，不区分大小写
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// 注意这句是关键，防止linux下 随机生成key。用其他方式在Windows上正常，但Linux上会有问题
			//[2].根据encryptEncodeRules规则初始化密钥生成器，生成一个128位的随机源,根据传入的字节数组，实现随机数算法
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			//[3].产生原始对称密钥
			SecretKey secretKey = kgen.generateKey();*/
            //使用指定秘钥加密
            SecretKey secretKey = new SecretKeySpec(password.getBytes("UTF-8"), "AES");
            //[4].获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //[5].根据字节数组生成AES密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            //[6].根据指定算法AES创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            //[7].获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byteContent = content.getBytes("utf-8");
            //[8].初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            //[9].根据密码器的初始化方式--加密：将数据加密
            //[10].将字节数组返回 加密
            return cipher.doFinal(byteContent);
        } catch (Exception e) {
            logger.error("AES加密异常", e);
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     * @Description：解密流程： 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.将加密后的字符串反生成byte[]数组
     * 6.将加密内容解密
     */
    private static byte[] decrypt(byte[] content, String password) {
        try {
			/*//[1].利用KeyGenerator构造密钥生成器，指定为AES算法，不区分大小写
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// 防止linux下 随机生成key
			//[2].根据encryptEncodeRules规则初始化密钥生成器，生成一个128位的随机源,根据传入的字节数组，实现随机数算法
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			//[3].产生原始对称密钥
			SecretKey secretKey = kgen.generateKey();*/
            //使用指定秘钥加密
            SecretKey secretKey = new SecretKeySpec(password.getBytes("UTF-8"), "AES");
            //[4].获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //[5].根据字节数组生成AES密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            //[6].根据指定算法AES创建密码器
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            //[7].初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            //[8]将加密并编码后的内容解码成字节数组
            byte[] result = cipher.doFinal(content);
            //[9].将字节数组返回
            return result; // 解密
        } catch (Exception e) {
            logger.error("AES解密异常", e);
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
