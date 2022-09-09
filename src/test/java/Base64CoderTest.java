import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class Base64CoderTest {

    private static final Base64Coder base64coder = new Base64Coder();

    @Test
    void encode() {
        String s = "new Jeans!";
        String encodedS = base64coder.encode(s);
        System.out.println(encodedS);
        assertThat(encodedS).isEqualTo("bmV3IEplYW5zIQ==");
    }

    @Test
    void imageEncode() {
        try {
            File file = new File("src/test/resources/Rubber_Duck_(8374802487).jpg");
            String encodedFile = base64coder.encode(file);
            System.out.println(encodedFile);

        } catch (Exception e) {
            System.out.println("뭐고");
        }
    }

    @Test
    void imageEncode_noFile() {
        File file = new File("");
//        base64coder.encode(file);
        assertThatThrownBy(
                () -> base64coder.encode(file)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void urlImgEncode_x_icon() {
        urlImgEncode("https://upload.wikimedia.org/favicon.ico");
    }

    @Test
    void urlImgEncode_no_contentType() {
        urlImgEncode("https://media.discordapp.net/attachments/998535054219288628/1015641819167592458/unknown.png");
    }

    @Test
    void urlImgEncode_index() {
        String encodedImg = urlImgEncode("https://upload.wikimedia.org/wikipedia/commons/1/14/Rubber_Duck_%288374802487%29.jpg");
    }

    @Test
    void urlImgEncode_gif() {
        urlImgEncode("https://c.tenor.com/JXvQS9se0ncAAAAC/duck-rubber-duck.gif");
    }

    @Test
    void urlImgEncode_html() {
        urlImgEncode("https://full-of-bluff.tistory.com/");
    }

    private String urlImgEncode(String url) {
        try {
            String result = base64coder.encodeImageInURL(url);
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}