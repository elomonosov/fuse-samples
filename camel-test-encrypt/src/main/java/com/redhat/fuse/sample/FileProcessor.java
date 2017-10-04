package com.redhat.fuse.sample;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.File;

public class FileProcessor implements Processor {

    private static int count = 0;

    @Override
    public void process(Exchange exchange) throws Exception {
        String path = "/host/share/";
        File f;
        for (int i = 0; i <= 10; i++) {
            count++;
            f = new File(path + String.valueOf(count));
            f.createNewFile();
        }
    }
}
