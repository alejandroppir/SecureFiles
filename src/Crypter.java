import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
 
public class Crypter {
 
    public static byte[] encrypt(byte[] texto,String pass) {
 
        byte[] base64Bytes=null;
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
            //base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64Bytes;
    }
 
    public static byte[] decrypt(byte[] textoEncriptado, String pass) throws Exception {
 
        byte[] plainText=null;
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(pass.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            plainText = decipher.doFinal(message);
 
 
        } catch (Exception ex) {
        }
        return plainText;
    }
}