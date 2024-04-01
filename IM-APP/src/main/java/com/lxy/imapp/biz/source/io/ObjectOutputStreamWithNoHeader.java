package com.lxy.imapp.biz.source.io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectOutputStreamWithNoHeader extends ObjectOutputStream {
    public ObjectOutputStreamWithNoHeader(OutputStream out) throws IOException {
        super(out);
    }

    protected ObjectOutputStreamWithNoHeader() throws IOException, SecurityException {
    }

    @Override
    protected void writeStreamHeader() throws IOException {

    }
}
