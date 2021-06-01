package cipher;

import java.io.InputStream;
import java.io.OutputStream;

public interface Cipher {
    OutputStream encode(OutputStream out);
    InputStream decode(InputStream in);
}