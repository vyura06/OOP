package cipher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BitCipher implements Cipher {
    private final int key;

    public BitCipher(int key) {
        this.key = key;
    }

    public OutputStream encode(OutputStream out) {
        BitGenerator g = new BitGenerator(key);
        return new OutputStream() {
            public synchronized void write(int b) throws IOException {
                out.write((b ^ g.nextByte()) & 0xFF);
            }

            public void flush() throws IOException {
                out.flush();
            }

            public void close() throws IOException {
                out.close();
            }
        };
    }
    public InputStream decode(InputStream in) {
        BitGenerator g = new BitGenerator(key);
        return new InputStream() {
            public synchronized int read() throws IOException {
                int r = in.read();
                return r == -1 ? r : (r ^ g.nextByte());
            }

            public int available() throws IOException {
                return in.available();
            }

            public long skip(long n) throws IOException {
                return in.skip(n);
            }

//            public void skipNBytes(long n) throws IOException {
//                in.skipNBytes(n);
//            }

            public void close() throws IOException {
                in.close();
            }

            public void mark(int readlimit) {
                in.mark(readlimit);
            }

            public void reset() throws IOException {
                in.reset();
            }

            public boolean markSupported() {
                return in.markSupported();
            }
        };
    }
}