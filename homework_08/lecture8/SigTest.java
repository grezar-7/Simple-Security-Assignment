package lecture8;

import org.junit.Test;

import java.security.KeyPairGenerator;

public class SigTest {
@Test
public void testConstructor() throws Exception {
    Sig sigTest = new Sig(1);
    KeyPairGenerator testKey = sigTest.keyGen;
    String testStringKey = testKey.getAlgorithm();
    org.junit.Assert.assertEquals("RSA", testStringKey);
}

    @Test
    public void testInitKeyPair() {

    }

    @Test
    public void getPublicKey() {
    }

    @Test
    public void getSignature() {
    }

    @Test
    public void verify() {
    }

    @Test
    public void main() {
    }
}