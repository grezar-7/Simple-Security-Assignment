package lecture8;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Sig {
	KeyPairGenerator keyGen;
	Signature signature;
	KeyFactory keyFactory;
	KeyPair keyPair;
	
	/*
	 * initialize a RSA key generator object with given key size
	 * initialize a signature object with SHA1withRSA algorithm
	 * initialize a RSA key factory object
	 */
	Sig(int keySize) throws Exception {
		keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		signature = Signature.getInstance("SHA1withRSA"); //Lecture Slide 31 ts 1:37
		keyFactory = keyFactory.getInstance("RSA");
	}
	
	// geenerate a public/private key pair
	void initKeyPair() {

		keyPair = keyGen.generateKeyPair();
	}
	
	// return the string encoding of the public key
	String getPublicKey() {

		PublicKey pub = keyPair.getPublic();
		String pubString = Base64.getEncoder().encodeToString(pub.getEncoded());
		return pubString;
	}
	
	// return the string encoding of the digital signature of the 'message'
	String getSignature(String message) throws Exception {

		PrivateKey privateKey = keyPair.getPrivate();
		signature.initSign(privateKey);
		signature.update(message.getBytes());
		byte[] digitalSignature = signature.sign();
		String sigMessage = Base64.getEncoder().encodeToString(digitalSignature);
		return sigMessage;
	}

	// return true if and only if the signature of the 'message' using the 'publicKey' matches the 'signatureString' 
	boolean verify(String message, String signatureString, String publicKey) throws Exception {
		// TODO
		byte[] publicKeyEncoded = Base64.getDecoder().decode(publicKey);
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyEncoded);
		PublicKey pub = keyFactory.generatePublic(publicKeySpec);
		signature.initVerify(pub);
		byte[] signatureStringInByte = Base64.getDecoder().decode(signatureString);
		signature.update(message.getBytes());
		boolean verified = signature.verify(signatureStringInByte);
		return verified;
	}
	
	public static void main(String[] args) {
		try {
			Sig sig = new Sig(1024);
			sig.initKeyPair();
			String publicKey = sig.getPublicKey(); 
			
			String message = "This is a test message that needs to be signed";
			
			String signatureString = sig.getSignature(message);
			boolean verified = sig.verify(message, signatureString, publicKey);
		
			System.out.printf("public key: %s\n\nsignature: %s\n\nsignature is verified: %s\n\n", 
					publicKey, signatureString, verified);
			
			String message1 = "This is a test message that needs to be verified";
			
			publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChBA91S3XSUYILZyhu5ilsiDjmzOZn830xxUEPLpeLQwr2BeLAtlCBqzihUcr5lGr9YvLN/jOta0TnEpQoa6kJYbUPnAZinh8E2Q4COfSx4Js3+TC64AD2yvlLaGOsBrDaBN15OGq9k2FgfxzxepVEPBW6DKHCeDQqPQAO8/UDXwIDAQAB";
			signatureString = "VvLONhPbhkzhmR3Z5v4WQfleSm8CR1CLwDWXjDGu4NhzN6L7n7zJBxj+z6WT8W3hb2Le69gKO2WIy/7kNvfqOTE6hOGz68YB0idZoATB2C7m9dSjRcdL0nYxThoPrfZXVDuMVuSpDo2We/M/8a4oCAPzjCopaCsDtTac+ZcL7/w=";
			verified = sig.verify(message1, signatureString, publicKey);
			
			System.out.printf("public key: %s\n\nsignature: %s\n\nsignature is verified: %s\n\n", 
					publicKey, signatureString, verified);
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
