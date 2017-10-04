package com.redhat.fuse.sample;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.activation.DataHandler;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * Created by elomonos on 6/29/17.
 */
public class TestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Map<String, DataHandler> attachments = exchange.getIn().getAttachments();
        if (attachments.size() > 0) {
            for (String name : attachments.keySet()) {
                DataHandler dh = attachments.get(name);
                // get the file name
                String filename = dh.getName();

                // get the content and convert it to byte[]
                byte[] data = exchange.getContext().getTypeConverter()
                        .convertTo(byte[].class, dh.getInputStream());

                // write the data to a file
                FileOutputStream out = new FileOutputStream(filename);
                out.write(data);
                out.flush();
                out.close();
            }
        }
    }
}
