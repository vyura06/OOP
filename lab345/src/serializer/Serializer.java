package serializer;

import transport.Transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public interface Serializer {
    void save(OutputStream out, Collection<? extends Transport> ts) throws Exception;
    Collection<? extends Transport> load(InputStream in) throws Exception;
}