package jwt;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class JWTSingleKeyTest {
    private static final String secretKey = "JWT_SINGLE_KEY_TEST_SECRET_KEY";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private static final JWTSingleKey jwtManagement =
            new JWTSingleKey(secretKey, signatureAlgorithm);

    @Test
    void generateToken(){
        Map<String, Object> map = getPayload();

        String token = jwtManagement.generateToken(map);
        String supplierToken = jwtManagement.generateToken(() -> map);

        String testToken = createTestToken();

        assertThat(token).isEqualTo(testToken);
        assertThat(supplierToken).isEqualTo(testToken);
    }

    @Test
    void validation() {
        String expiredToken = createExpiredToken();
        String emptyClaimsToken = createEmptyClaimsToken();
        String invalidClaimsToken = createInvalidClaimsToken();
        String invalidSignatureToken = createInvalidSignatureToken();

        assertThatThrownBy(
                () -> jwtManagement.tokenValidation(expiredToken)
        ).isInstanceOf(ExpiredJwtException.class);
        assertThatThrownBy(
                () -> jwtManagement.tokenValidation(emptyClaimsToken)
        ).isInstanceOf(MalformedJwtException.class);
        assertThatThrownBy(
                () -> jwtManagement.tokenValidation(invalidClaimsToken)
        ).isInstanceOf(UnsupportedJwtException.class);
        assertThatThrownBy(
                () -> jwtManagement.tokenValidation(invalidSignatureToken)
        ).isInstanceOf(SignatureException.class);
    }

    String createTestToken() {
        return createToken(null, null, null, null);
    }

    String createExpiredToken() {
        Date exp = new Date(System.currentTimeMillis() - 1000 * 60 * 60L * 24 * 14);
        return createToken(exp, null, null, null);
    }

    String createEmptyClaimsToken() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9..BuOHgP5Nf0NHaZsZbFmM9v1csZh2iKQD3E3vemx7atM";
    }

    String createInvalidClaimsToken() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyIiOiIifQ.gvZ9YQS--xhsDcCq7zu16OFsVDioP98nYxenfuO2DHA";
    }

    String createInvalidSignatureToken() {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEyMyJ9.Sco7us0RTAg8Eg90FLosOkLriMccYtmXydfwZYlE6F4";
    }

    String createToken(Date exp, Key key, Map<String, Object> header, Map<String, Object> payload) {
        if (exp == null) {
            exp = getExp();
        }
        if (key == null) {
            key = getKey();
        }
        if (header == null) {
            header = getHeader();
        }
        if (payload == null) {
            payload = getPayload();
        }

        return Jwts.builder()
                .setHeader(header)
                .setClaims(payload)
                .setExpiration(exp)
                .signWith(signatureAlgorithm, key)
                .compact();
    }

    Map<String, Object> getPayload() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("sub", "user");
        payload.put("id", 123L);
        return payload;
    }

    Map<String, Object> getHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("alg", signatureAlgorithm);
        header.put("typ", "JWT");
        return header;
    }

    Key getKey() {
        return new SecretKeySpec(
                secretKey.getBytes(StandardCharsets.UTF_8),
                signatureAlgorithm.getJcaName()
        );
    }

    Date getExp() {
        return new Date(System.currentTimeMillis() + 1000 * 60 * 60L * 24 *14);
    }
}
