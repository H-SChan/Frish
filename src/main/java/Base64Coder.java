import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Coder {

    /**
     * use {@link Base64.Encoder#encodeToString(byte[])} to convert a string to Base64
     * @param s A string to convert to base64
     * @return A String containing the resulting Base64 encoded string
     * @see Base64.Encoder#encodeToString(byte[])
     */
    public String encode(String s) {
        byte[] byteS = s.getBytes(StandardCharsets.UTF_8);
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(byteS);
    }

    public String encode(byte[] b) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(b);
    }

    /**
     *
     * @param file the file to convert to Base64
     * @return A String containing the resulting Base64 encoded file
     * @throws IOException if an I/O error occurs
     * @throws SecurityException if a security manager exists and its checkRead method denies read access to the file
     * @see Base64Coder#encode(byte[])
     */
    public String encode(File file) throws IOException {
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int len;
            byte[] buf = new byte[1024];
            while ((len = fileInputStream.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
            }

            byte[] fileArray = byteArrayOutputStream.toByteArray();
            return encode(fileArray);
        } else {
            throw new IllegalArgumentException("file is not exists!");
        }
    }

}
