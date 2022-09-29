import io.github.hsc.Crypto;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.*;

class CryptoTest {
    @Test
    void sha___() {
        Crypto crypto = new Crypto();
        String hashText1 = "";
        String hashText2 = "";
        String hashText3 = "";
        String hashText4 = "";
        try {
            hashText1 = crypto.encode("SHA-256", "갈수록 짙어져가");
            System.out.println(hashText1);
            hashText2 = crypto.encode("SHA-1", "갈수록 짙어져가");
            System.out.println(hashText2);
            hashText3 = crypto.encode("SHA-512", "갈수록 짙어져가");
            System.out.println(hashText3);
            hashText4 = crypto.encode("SHA-384", "갈수록 짙어져가");
            System.out.println(hashText4);
        } catch (Exception e) {
            System.out.println("에러 발생 " + e.getMessage());
            e.printStackTrace();
        }
        assertThat(hashText1).isEqualTo("b6b47f9c1bcda6c383c36c9299e897b704c0046520e3232af3b8127ecf80697c");
        assertThat(hashText2).isEqualTo("bea8ad59d5ff7a9a553289ac972357a605a1bc90");
        assertThat(hashText3).isEqualTo("d3cfdcdab77772fbf2cc87dd8b5b38735c60bc8a79c6f3e5a166e2e067b64be48d51411808500b0768ade3066aca520d1c643331c85486a1295130b950153e6c");
        assertThat(hashText4).isEqualTo("a1aaae521c908a8a5b2577642127309ddc1b8dab170fd128ba418b31f5d81e9d17bc1cf4448726101cb7d5667340a8c6");
    }

    @Test
    void md_() {
        Crypto crypto = new Crypto();
        String hashText1 = "";
        String hashText2 = "";
        try {
            hashText1 = crypto.encode("MD5", "갈수록 짙어져가");
            System.out.println(hashText1);
            hashText2 = crypto.encode("MD2", "갈수록 짙어져가");
            System.out.println(hashText2);
        } catch (Exception e) {
            System.out.println("에러 발생 " + e.getMessage());
            e.printStackTrace();
        }
        assertThat(hashText1).isEqualTo("0c262b1b82001f4f38a5a3cf2f06944f");
        assertThat(hashText2).isEqualTo("2a862d7974e933f446b3979efedb406b");
    }

    private static class Cc extends Crypto{
        private final Crypto crypto;

        public Cc() {
            this.crypto = new Crypto();
        }

        @Override
        public String encode(String algorithm, String str) throws NoSuchAlgorithmException {
            return super.encode(algorithm, str);
        }

        public boolean decode(String algorithm, String target, String hash) throws NoSuchAlgorithmException {
            return crypto.decode(algorithm, target, hash);
        }

    }

    @Test
    void sha3___() {
        Cc crypto = new Cc();
        String hashText1 = "";
        String hashText2 = "";
        String hashText3 = "";
        String hashText4 = "";
        try {
            hashText1 = crypto.encode("SHA3-224", "갈수록 짙어져가");
            System.out.println(hashText1);
            hashText2 = crypto.encode("SHA3-256", "갈수록 짙어져가");
            System.out.println(hashText2);
            hashText3 = crypto.encode("SHA3-384", "갈수록 짙어져가");
            System.out.println(hashText3);
            hashText4 = crypto.encode("SHA3-512", "갈수록 짙어져가");
            System.out.println(hashText4);
        } catch (Exception e) {
            System.out.println("에러 발생 " + e.getMessage());
            e.printStackTrace();
        }
        assertThat(hashText1).isEqualTo("13c0efc39538534fd47fa64e15c7783353296307a64f5f048001104b");
        assertThat(hashText2).isEqualTo("2c333918dfe6f5d75bb66e2c0b822f0fba88f7aaac81a90072877ec862c3f244");
        assertThat(hashText3).isEqualTo("40e3903d69e56355865b31f94a218685f11d7a4fe5cd77089e116b36e603844c6175f019c18f0c70febf9abde2ef22a6");
        assertThat(hashText4).isEqualTo("0f70ca0865df991e961151e54d3cabe57b8b9ebf339c7839fc9108a7fdc96ff6e4bb5f25900c7b6460573e1da193c7d92d5cc28f6a718bae64e8af82490f1e40");
    }
}
