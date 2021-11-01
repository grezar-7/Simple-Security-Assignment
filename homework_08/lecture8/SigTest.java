package lecture8;

import org.junit.Test;

import java.security.KeyPairGenerator;

class SigTest {
@Test
void testConstructor() throws Exception {
    Sig sigTest = new Sig(1);
    KeyPairGenerator testKey = sigTest.keyGen;
    String testStringKey = testKey.getAlgorithm();
    org.junit.Assert.assertEquals("RSA", testStringKey);
}

    @Test
    void initKeyPair() {

    }

    @Test
    void getPublicKey() {
    }

    @Test
    void getSignature() {
    }

    @Test
    void verify() {
    }

    @Test
    void main() {
    }
}