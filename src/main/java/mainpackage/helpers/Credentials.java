package mainpackage.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class Credentials {

  private static Credentials instance = null;

  public static Credentials instance() {
    if (instance == null)
      instance = new Credentials();
    return instance;
  }

  public String clientId(String name, String url) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("md5");
      md5.update(name.getBytes());
      md5.update(url.getBytes());
      byte[] bytes = md5.digest();
      BigInteger n = new BigInteger(1, bytes);
      return String.format("%032x", n);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String clientSecret(String client_id, String name, String url) {
    try {
      MessageDigest sha256 = MessageDigest.getInstance("sha-256");
      sha256.update(client_id.getBytes());
      sha256.update(name.getBytes());
      sha256.update(url.getBytes());
      byte[] bytes = sha256.digest();
      BigInteger n = new BigInteger(1, bytes);
      return String.format("%032x", n);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String authorizationCode(String clientId) {
    try {
      MessageDigest sha256 = MessageDigest.getInstance("sha-256");
      sha256.update(clientId.getBytes());
      byte[] bytes = sha256.digest();
      BigInteger n = new BigInteger(1, bytes);
      return String.format("%032x", n);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String accessToken(String clientId, String code, String redirectUri) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("md5");
      Long time = Instant.now().getEpochSecond();
      md5.update(time.byteValue());
      md5.update(clientId.getBytes());
      md5.update(code.getBytes());
      md5.update(redirectUri.getBytes());
      byte[] bytes = md5.digest();
      BigInteger n = new BigInteger(1, bytes);
      return String.format("%032x", n);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  public String refreshToken(String clientSecret, String code, String redirectUri) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("md5");
      Long time = Instant.now().getEpochSecond();
      md5.update(time.byteValue());
      md5.update(clientSecret.getBytes());
      md5.update(code.getBytes());
      md5.update(redirectUri.getBytes());
      byte[] bytes = md5.digest();
      BigInteger n = new BigInteger(1, bytes);
      return String.format("%032x", n);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

}