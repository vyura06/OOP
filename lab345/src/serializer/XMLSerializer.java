package serializer;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import transport.Transport;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

public class XMLSerializer implements Serializer {
    private static class InstanceHolder {
        static final XMLSerializer INSTANCE = new XMLSerializer();
    }
    public static XMLSerializer getInstance() {
        return XMLSerializer.InstanceHolder.INSTANCE;
    }

    private XMLSerializer() {
    }

    private final Charset charset = StandardCharsets.UTF_8;
    private final XMLOutputFactory factory = XMLOutputFactory.newInstance();
    private final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    public void save(OutputStream out, Collection<? extends Transport> ts) throws Exception {
        XMLStreamWriter w = factory.createXMLStreamWriter(new OutputStreamWriter(out, charset));
        try {
            w.writeStartDocument(charset.displayName(), "1.0");
            w.writeStartElement("Transports");

            for (Transport t : ts)
                t.write(w);

            w.writeEndElement();
            w.writeEndDocument();
        } finally {
            w.close();
        }
    }
    public Collection<? extends Transport> load(InputStream in) throws Exception {
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(in);
        Node root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        ArrayList<Transport> ts = new ArrayList<>(nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            Node item = nodes.item(i);
            if (item.getNodeType() != Node.TEXT_NODE) {
                Transport t = Transport.read(item);
                if (t != null)
                    ts.add(t);
            }
        }
        return ts;
    }
}