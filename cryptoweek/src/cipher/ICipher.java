package cipher;

import java.io.File;

public interface ICipher {
    void encode(File message, File key, File encrypted);
    void decode(File message, File key, File crypted);
    Object generateKey();
    Object readKey(File f);
    void writeKey(File f);
}
