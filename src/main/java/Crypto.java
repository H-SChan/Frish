import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
    public String encode(String algorithm, String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(str.getBytes());
        return bytesToHex(md.digest());
    }

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
