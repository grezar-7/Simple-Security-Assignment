package lecture8;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Digest {
	/*
	 * print out the hash digest of the string 
	 * "test message to be hashed" using the SHA-256 algorithm
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO
		String testMessage = "test message to be hashed";
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] messageHashedArray = digest.digest(testMessage.getBytes());

	 String hashedMessage = Base64.getEncoder().encodeToString(messageHashedArray);

	 System.out.println("Original message is: " + testMessage);
	 System.out.println("Hashed Message is: " + hashedMessage);
	}
}
