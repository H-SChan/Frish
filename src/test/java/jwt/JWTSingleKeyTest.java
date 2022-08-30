package jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class JWTSingleKeyTest {
    private static final JWTManagement jwtManagement =
            new JWTSingleKey("JWT_SINGLE_KEY_TEST_SECRET_KEY", SignatureAlgorithm.HS256);

    @Test
    void generateToken() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        map.put("sub", "user");
        map.put("id", 123L);

        String token = jwtManagement.generateToken(map);

        assertThat(token).isEqualTo(createTestToken());
    }

    String createTestToken() {
        String secretKey = "JWT_SINGLE_KEY_TEST_SECRET_KEY";
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60L * 24 * 14);
        Key key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", SignatureAlgorithm.HS256);
        headerMap.put("typ", "JWT");

        Map<String, Object> map = new HashMap<>();
        map.put("sub", "user");
        map.put("id", 123L);

        return Jwts.builder()
                .setHeader(headerMap)
                .setClaims(map)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
