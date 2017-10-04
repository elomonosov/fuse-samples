package com.redhat.fuse.sample;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elomonos on 5/11/17.
 */
public class GeneratorProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);

        exchange.getOut().setBody(integerList);
    }
}
