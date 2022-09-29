package io.github.hsc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * can SHA, MD
 */
public class Crypto {
    /**
     * encode a string
     * @param algorithm want algorithm in SHA or MD
     * @param str want to encode
     * @return encoded string what argument str to argument algorithm
     * @throws NoSuchAlgorithmException can't find algorithm or Illegal algorithm
     */
    public String encode(String algorithm, String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(str.getBytes());
        return bytesToHex(md.digest());
    }

    /**
     * hashed string is same to target string
     * @param algorithm an algorithm used for hash string encoding
     * @param target Expected string as source of encoded string
     * @param hash encoded string
     * @return is same target string encoded to algorithm with hash
     * @throws NoSuchAlgorithmException can't find algorithm or Illegal algorithm
     */
    public boolean decode(String algorithm, String target, String hash) throws NoSuchAlgorithmException {
        return this.encode(algorithm, target).equals(hash);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte aByte : bytes) {
            builder.append(String.format("%02x", aByte));
        }
        return builder.toString();
    }
}
