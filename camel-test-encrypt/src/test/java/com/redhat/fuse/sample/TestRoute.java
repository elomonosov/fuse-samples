package com.redhat.fuse.sample;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/spring/test-context.xml", "/META-INF/spring/camel-context.xml"},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@UseAdviceWith
public class TestRoute {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected ModelCamelContext camelContext;

    @Test
    @Ignore
    public void cronTest() throws Exception {
        camelContext.start();
        Thread.sleep(50000);
    }

    @Test
    @Ignore
    public void name() throws Exception {
        camelContext.getRouteDefinition("splitterRoute")
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        replaceFromWith("direct:inTest");
                    }
                });
        camelContext.start();
        ProducerTemplate template = camelContext.createProducerTemplate();
        String body = "This is test message";
        template.sendBody("direct:inTest", body);
    }

    @Test
    @Ignore
    public void testMailClient() throws Exception {
        camelContext.getRouteDefinition("mailClient")
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        replaceFromWith("direct:inTest");
                    }
                });
        camelContext.start();
        ProducerTemplate template = camelContext.createProducerTemplate();

        Map<String, Object> map = new HashMap<>();
        map.put("To", "davsclaus@apache.org");
        map.put("From", "jstrachan@apache.org");
        map.put("Subject", "Camel rocks");
        map.put("CamelFileName", "fileOne");
        map.put("org.apache.camel.test", "value");

        String body = "This is test message";

//        template.sendBodyAndHeaders("smtp://test@apache.org", body, map);
    }
}
