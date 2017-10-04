package com.redhat.fuse.sample;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Counter implements Processor {

    private static int counter = 0;

    @Override
    public void process(Exchange exchange) throws Exception {
        counter++;
        System.out.println("Count: " + counter);
    }
}
