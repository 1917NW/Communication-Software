package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

public class ObjectInputStreamWithNoHeader extends ObjectInputStream {
    public ObjectInputStreamWithNoHeader(InputStream in) throws IOException {
        super(in);
    }

    protected ObjectInputStreamWithNoHeader() throws IOException, SecurityException {
    }

    @Override
    protected void readStreamHeader() throws IOException, StreamCorruptedException {

    }
}
