import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Crypter {

	public static byte[] encrypt(byte[] texto, String pass) {

		byte[] base64Bytes = null;

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(pass.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto;
			byte[] buf = cipher.doFinal(plainTextBytes);
			base64Bytes = Base64.encodeBase64(buf);

		} catch (NoSuchAlgorithmException e) {
			System.out.println("mensaje de crypter 1");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("mensaje de crypter 2");
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			System.out.println("mensaje de crypter 3");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			System.out.println("mensaje de crypter 4");
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			System.out.println("mensaje de crypter 5");
			e.printStackTrace();
		} catch (BadPaddingException e) {
			System.out.println("mensaje de crypter 6");
			e.printStackTrace();
		}

		return base64Bytes;
	}

	public static byte[] decrypt(byte[] textoEncriptado, String pass) {

		byte[] plainText = null;
		try {

			byte[] message = Base64.decodeBase64(textoEncriptado);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(pass.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);

			plainText = decipher.doFinal(message);

		} catch (NoSuchAlgorithmException e) {
			System.out.println("mensaje de decrypter 1");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("mensaje de decrypter 2");
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			System.out.println("mensaje de decrypter 3");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			System.out.println("mensaje de decrypter 4");
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			System.out.println("mensaje de decrypter 5");
			e.printStackTrace();
		} catch (BadPaddingException e) {
			System.out.println("mensaje de decrypter 6");
			e.printStackTrace();
		}

		return plainText;
	}
}