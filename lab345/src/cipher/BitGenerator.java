package cipher;

//x32 + x28 + x27 + x + 1
public class BitGenerator {
    private int bits;

    public BitGenerator(int bits) {
        this.bits = bits;
    }

    public int getBits() {
        return bits;
    }
    public int nextBit() {
        final int firstBit = bits >>> 31;
        int newBit = (firstBit ^ (bits >>> 27) ^ (bits >>> 26) ^ bits) & 1;
        bits = (bits << 1) | newBit;
        return (firstBit & 1);
    }
    public int nextByte() {
        return next(8);
    }
    public int nextShort() {
        return next(16);
    }
    public int nextInt() {
        return next(32);
    }
    private int next(int count) {
        int key = 0;
        for (int i = 0; i < count; i++) {
            key <<= 1;
            key |= nextBit();
        }
        return key;
    }
}