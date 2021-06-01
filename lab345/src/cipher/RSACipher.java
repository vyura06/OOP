package cipher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Objects;

public class RSACipher implements Cipher {
    public static boolean isNotPrime(BigInteger n) {
        BigInteger prev = n.subtract(BigInteger.ONE);
        return !(prev.signum() > 0 && prev.nextProbablePrime().compareTo(n) == 0);
    }
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }
    public static BigInteger fastExp(BigInteger a, BigInteger z, BigInteger n) {
        BigInteger a1 = a;
        BigInteger z1 = z;
        BigInteger x = BigInteger.ONE;
        while (z1.signum() != 0) {
            while (z1.mod(BigInteger.TWO).signum() == 0) {
                z1 = z1.shiftRight(1);
                a1 = a1.multiply(a1).mod(n);
            }
            z1 = z1.subtract(BigInteger.ONE);
            x = x.multiply(a1).mod(n);
        }
        return x;
    }
    public static BigInteger[] advancedEuclideanAlgorithm(BigInteger a, BigInteger b) {
        BigInteger d0 = a;
        BigInteger d1 = b;
        BigInteger x0 = BigInteger.ONE;
        BigInteger x1 = BigInteger.ZERO;
        BigInteger y0 = BigInteger.ZERO;
        BigInteger y1 = BigInteger.ONE;


        while (d1.compareTo(BigInteger.ONE) > 0) {
            BigInteger q = d0.divide(d1);
            BigInteger d2 = d0.mod(d1);
            BigInteger x2 = x0.subtract(q.multiply(x1));
            BigInteger y2 = y0.subtract(q.multiply(y1));
            d0 = d1;
            d1 = d2;
            x0 = x1;
            x1 = x2;
            y0 = y1;
            y1 = y2;
        }
        return new BigInteger[]{x1, y1, d1};
    }
    public static BigInteger modInverse(BigInteger e, BigInteger f) {
        if (!(e.compareTo(BigInteger.ONE) > 0))
            throw new ArithmeticException("e should be > 1");
        if (!(e.compareTo(f) < 0))
            throw new ArithmeticException("e should be less then f(r)=" + f);
        if (gcd(e, f).compareTo(BigInteger.ONE) != 0)
            throw new ArithmeticException("e should be mutually simple with f(r)=" + f);
        BigInteger[] coefs = advancedEuclideanAlgorithm(f, e);
        BigInteger y1 = coefs[1];
        while (y1.signum() < 0)
            y1 = y1.add(f);
        return y1;
    }

    private static final int CIPHER_BYTES_COUNT = 4;

    private final BigInteger e, d, r;
    public RSACipher(BigInteger p, BigInteger q, BigInteger e) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(q);
        Objects.requireNonNull(e);
        if (isNotPrime(q)) throw new ArithmeticException("q should be prime");
        if (isNotPrime(p)) throw new ArithmeticException("p should be prime");
        BigInteger r = p.multiply(q);
        if (r.compareTo(BigInteger.valueOf(0xFF)) < 0)
            throw new RuntimeException("R very small");

        BigInteger f = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = modInverse(e, f);

        this.e = e;
        this.d = d;
        this.r = r;
    }

    public OutputStream encode(OutputStream out) {
        Objects.requireNonNull(out);
        return new OutputStream() {
            public synchronized void write(int b) throws IOException {
                byte[] bytes = fastExp(BigInteger.valueOf(b & 0xFFL), e, r).toByteArray();
                if (bytes.length > CIPHER_BYTES_COUNT)
                    throw new RuntimeException();
                //если массив байтов BigInteger меньше заполняем 0 недостающие значения
                for (int k = bytes.length; k < CIPHER_BYTES_COUNT; k++)
                    out.write(0);
                out.write(bytes);
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
        Objects.requireNonNull(in);
        return new InputStream() {
            private final byte[] buf = new byte[CIPHER_BYTES_COUNT];

            public synchronized int read() throws IOException {
                int l = in.read(buf, 0, buf.length);
                if (l == -1)
                    return -1;
                while (l < buf.length)
                    buf[l++] = 0;
                int k = CIPHER_BYTES_COUNT;
                while (k > 0 && buf[k - 1] == 0)
                    k--;
                // calc offset = (i + (CIPHER_BYTES_COUNT - k))
                byte[] bytes = fastExp(new BigInteger(buf, (CIPHER_BYTES_COUNT - k), k), d, r).toByteArray();
                return bytes[0] == 0 && bytes.length > 1 ? bytes[1] : bytes[0];
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

            public synchronized void mark(int readlimit) {
                in.mark(readlimit);
            }

            public synchronized void reset() throws IOException {
                in.reset();
            }

            public boolean markSupported() {
                return in.markSupported();
            }
        };
    }
}