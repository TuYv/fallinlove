package com.max.fallinlove.base.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author max
 * @date 2021/8/11 16:19
 */
public class RSAUtil {
    // 加密算法
    public static final String algorithm = "RSA";
    // 转换模式
    public static final String transform = "RSA/ECB/PKCS1Padding";
    // 最大加密块长度 1024/8 - 11
    private static final int MAX_ENCRYPT_BLOCK = 117;
    // 最大解密块长度 1024/8
    private static final int MAX_DECRYPT_BLOCK = 64;
    // RSA公钥BASE64字符串
    public static final String rsaPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ5lcDfvDc6mo0WPHcqShz+BFrTenxQ/lOA/71r3p2fkKO5RDewqhcoEEa2IE/5qThHt1chDcaVH/jRYSiNjBCsCAwEAAQ==";
    // RSA私钥BASE64字符串
    public static final String rsaPrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAnmVwN+8NzqajRY8dypKHP4EWtN6fFD+U4D/vWvenZ+Qo7lEN7CqFygQRrYgT/mpOEe3VyENxpUf+NFhKI2MEKwIDAQABAkB2WIgd4ojIbAz1fdbecdiHhCW5AdSNxmuvmVV2/C6iGdCGQfnIw+rPl/RslwGfk1uRqE7i9iqaQ/uzzakBkyoBAiEA6fMpLkzmHOCl+If/YFA8+CEsFDdYHiXEd/tcovl65YECIQCtU0nrNtelcf7ExbyoZSKaBASUPfw/IzapIxdLh303qwIhALSL7N8Hh5sHn+6ifepFYhgX+Z2MbJuvh+UUfpRjtn2BAiBXnxJwWYI8/lsISNpt2uraJ4vVEVzy4i9g4/+DzFPuDwIgbegFwEca/fn64wfQLC9ralFLyivHn4pi+CuNWils5kw=";

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        System.out.println(decrypt("ftb2p28wRPKuOmu4Xw4gdaAriyHoEDKhuwoeMIiSD/8M/G7Zr1UjasU2B30gL5hbo1Y995In52uDZSub4C8BKCjOfMsMcSSEcAUx/r/TMfzpOTXEJUhbWwBLtiFkxDjnRoU1YV3q0Bvxhyh5bf7arKk2iLwU24m10zU3tNuxRIIyrRu80+6jZ+41VEJHewBlKUOxA0PqOoJOAlbmm3kg4k8UdAFoncgNiOjHEBWyC/mXCrZKmIT382ncdsmY5MRrdTYel3zzZVlg3utUK8s2UjH3d6pRXsSvLrgVuWq0IppsTgWkXBl/2eKGpuLM8Dyfs4ecBoh/zasW6O3+7v0UuCeya1DsiQgubm0UPS9KdEhunJrqobmzr1b4yF/KGpkpXzuWKE565N1ozzkv3qHSvMSOEzg1FEoyRTyUPQPc358CupCZY3JWZuMh4g4ScGCqF8vXTFT0Zj7OYS0Tx74uq2oiQhnvwXNmWQi0Sp55q3Xyj3sD/OLWtTaHkwiEXfuSfUA7j1vjfxo6bG+IboeWJjapAFfc9yy2EniR7jlX+Ow+7EpfUjqStN995VHgZDG3nJVnEXXbDIa+R8lxSf6EySrgqkPPIw1vT4QbJZ67jGgC2qR0GdAswrJ4j6pW0qqqImQPiOkwMB3jvq5Khlutql+gVhvcOAgfRc9hArRV7ZUErvv1GGULxHv5roL767stDgpm0+6padASbdRXECBpgGy8TeM5uFqvKDqoZJ4/RXpfEIrSPuq6XASOmNpq05PkQiiub6yCB3Zti/uzPeUVIH4FfFZ5xpKt4xYLUfWDhmM8bhR6IdxQCKhDzdcCVH7XX96ncCGdBdZScTbO7ey6VSbhn+JSCGMjH/ld8liqaTOKngmOeis8kSdJ+3WUbHnUGnrzXbTBDgcNUKd89HfxwTuEWqMiQqe4GrLRQO5TA4Zj6O60ZfHONwmYAq/5k2KIuzGslIOJkThaGewvv4IXsWy/WAkP2SzXQEExKq+Kc3z8/YUldOkYnUev2+TP2MPjbmqO6DelDmz9jta2aD1sr2utv4mqFogrPyPNyHIsNVCyNcMDQwKZ5a/K13S+uGpb2nxP8vDYrJrhH1fUySOIdEOc98XVIkyy6i4+F7Y3hUJYa1IeuBsTfHv1ql82HWjM7//2Vd+6lqH9aeBBfMpsoGgb42YJ1v+WB8aKBk+VU6Y="));
    }

    /**
     * 公钥加密
     *
     * @param value     待加密值
     * @return 加密值
     */
    public static String encrypt( String value) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Cipher cipher = Cipher.getInstance(transform);
            PublicKey publicKey = transPublicKey(rsaPublicKey);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = value.getBytes();
            int length = bytes.length;

            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (length - offSet > 0) {
                if (length - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(bytes, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(bytes, offSet, length - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 私钥解密
     * @param value    待解密值
     * @return 解密值
     */
    public static String decrypt (String value) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Cipher cipher = Cipher.getInstance(transform);
            PrivateKey privateKey = transPrivateKey(rsaPrivateKey);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] bytes = Base64.getDecoder().decode(value);
            int length = bytes.length;

            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (length - offSet > 0) {
                if (length - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(bytes, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(bytes, offSet, length - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            return new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换公钥
     * @param rsaPublicKey
     */
    public static PublicKey transPublicKey(String rsaPublicKey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(rsaPublicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

        return publicKey;
    }

    /**
     * 转换私钥
     */
    public static PrivateKey transPrivateKey(String rsaPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = Base64.getDecoder().decode(rsaPrivateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

        return privateKey;
    }
}
