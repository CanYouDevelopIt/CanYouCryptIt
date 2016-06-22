package cipher;

public interface IMonoCipher {
    String encode(String message, String key);
    String decode(String crypted, String key);
}