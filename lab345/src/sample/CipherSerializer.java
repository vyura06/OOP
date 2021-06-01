package sample;

import cipher.Cipher;
import serializer.Serializer;
import transport.Transport;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Objects;

class CipherSerializer implements Serializer {
    private final Serializer back;
    private final Cipher cipher;

    public CipherSerializer(Serializer back, Cipher cipher) {
        this.back = Objects.requireNonNull(back);
        this.cipher = Objects.requireNonNull(cipher);
    }

    public void save(OutputStream out, Collection<? extends Transport> ts) throws Exception {
        back.save(cipher.encode(out), ts);
    }

    public Collection<? extends Transport> load(InputStream in) throws Exception {
        return back.load(cipher.decode(in));
    }
}
