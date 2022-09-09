import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
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
     * @param file the file to convert to Base64
     * @return A String containing the resulting Base64 encoded file
     * @throws IOException              if an I/O error occurs
     * @throws IllegalArgumentException if an argument is not valid
     * @see Base64Coder#encode(byte[])
     */
    public String encode(File file) {
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
            int len;
            byte[] buf = new byte[1024];
            while ((len = fileInputStream.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
            }

            byte[] fileArray = byteArrayOutputStream.toByteArray();
            return encode(fileArray);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("file is not exists!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String encodeImageInURL(String url) throws IOException {
        URL requestUrl = new URL(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

        connection.setRequestMethod("GET");
        connection.setUseCaches(false);

        String contentType = connection.getHeaderField("content-type");
        if (contentType.matches("image*")) {
            connection.disconnect();
            throw new IllegalTypeException();
        }

        InputStream inputStream = connection.getInputStream();
        int len;
        byte[] buf = new byte[1024];
        while ((len = inputStream.read(buf)) != -1) {
            byteArrayOutputStream.write(buf, 0, len);
        }

        byte[] fileArray = byteArrayOutputStream.toByteArray();
        return encode(fileArray);


    }
}
