package com.max.fallinlove.base.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author max
 * @date 2021/6/15 21:13
 */
@Slf4j
public class JwtUtil {
    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 18000L;//单位为秒

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(String id) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("id", id)//userId
                .withExpiresAt(expireDate) //超时设置,设置过期的日期
                .withIssuedAt(new Date()) //签发时间
                .sign(Algorithm.HMAC256(SECRET)); //SECRET加密
        return token;
    }

    /**
     * 校验token并解析token中的id
     */
    public static String verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("token解码异常");
            //解码异常则抛出异常
            return null;
        }
        return jwt.getClaims().get("id").asString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String value = "NhRO/I78kLiZn4UP4400MXEbv6BJFYlV8HBionEqHNa/rLaYpKaDwj0BaFXOtCSLgPI7BZsINBxCAOAN0z0RAA==";
        // 加密算法
        String algorithm = "RSA";
        // 转换模式
        String transform = "RSA/ECB/PKCS1Padding";
        // 实例化秘钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 初始化，秘钥长度512~16384位，64倍数
        keyPairGenerator.initialize(16384);
        // 生成秘钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 公钥
        PublicKey publicKey = keyPair.getPublic();
        System.out.println("RSA公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        // 私钥
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("RSA私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        // ------ 测试公钥加密，私钥解密 ------
        Cipher cipher = Cipher.getInstance(transform);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] pubEncryptBytes = cipher.doFinal(value.getBytes());
        System.out.println("RSA公钥加密后数据: " + Base64.getEncoder().encodeToString(pubEncryptBytes));

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] priDecryptBytes = cipher.doFinal(pubEncryptBytes);
        System.out.println("RSA私钥解密后数据: " + new String(priDecryptBytes));

        // ------ 测试私钥加密，公钥解密 ------
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] priEncryptBytes = cipher.doFinal(value.getBytes());
        System.out.println("RSA私钥加密后数据: " + Base64.getEncoder().encodeToString(priEncryptBytes));

        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] pubDecryptBytes = cipher.doFinal(priEncryptBytes);
        System.out.println("RSA公钥解密后数据: " + new String(pubDecryptBytes));
    }
}
