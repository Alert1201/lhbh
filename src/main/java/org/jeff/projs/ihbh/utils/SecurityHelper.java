package org.jeff.projs.ihbh.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecurityHelper {

	public static byte[] getSalt(String algorithm) {
		byte[] salt = null;
		try {
			SecureRandom sr;
			sr = SecureRandom.getInstance(algorithm);
			// Create array for salt
			salt = new byte[16];
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt;
	}
}
