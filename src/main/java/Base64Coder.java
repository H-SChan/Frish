import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Coder {

    private final Charset charset;

    public Base64Coder() {
        this.charset = StandardCharsets.UTF_8;
    }

    public Base64Coder(Charset charset) {
        this.charset = charset;
    }


    /**
     * Use {@link Base64} to encode strings.
     * If you do not specify charset, use {@link java.nio.charset.StandardCharsets#UTF_8 UTF-8} by default.
     * The description below assumes that no charset is specified.
     *
     * <p>First, make it an array of bytes with UTF-8.
     * Then encode to {@link java.util.Base64 Base64} and convert to string
     * using UTF-8 charset
     *
     * @param s A string to convert to base64
     * @return A String containing the resulting Base64 encoded string
     * @see Base64.Encoder#encode(byte[]) encode(byte[])
     */
    public String encode(String s) {
        byte[] byteS = s.getBytes(charset);
        Base64.Encoder encoder = Base64.getEncoder();
        return new String(encoder.encode(byteS), charset);
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