import org.assertj.core.api.Assertions;
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
}