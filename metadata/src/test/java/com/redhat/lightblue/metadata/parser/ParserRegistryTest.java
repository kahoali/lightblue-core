package com.redhat.lightblue.metadata.parser;

import org.junit.Assert;
import org.junit.Test;

public class ParserRegistryTest {

    class TestParser implements Parser<Object, Object> {

        @Override
        public Object parse(String name, MetadataParser<Object> p, Object node) {
            return null;
        }

        @Override
        public void convert(MetadataParser<Object> p, Object emptyNode, Object object) {
        }
    }

    @Test
    public void get() {
        ParserRegistry<Object, Object> reg = new ParserRegistry<>();

        TestParser parser = new TestParser();

        String name = "foo";

        reg.add(name, parser);

        Assert.assertEquals(parser, reg.find(name));
    }
}
