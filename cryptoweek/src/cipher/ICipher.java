package cipher;

import java.io.File;

public interface ICipher {
    String encode(String message, Object key);
    String decode(String crypted, Object key);
    Object generateKey();
    Object readKey(File f);
    void writeKey(File f);
}
