package net.smartcosmos.edge.things.utility;

import org.junit.*;

import static org.junit.Assert.*;

public class ThingEdgeEventTypeTest {

    
    @Test
    public void testUnknownFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.UNKNOWN;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("miscellaneous");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullFromString() throws Exception {
        ThingEdgeEventType.fromString(null);
        assertTrue("IllegalArgumentException expected.", Boolean.FALSE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlankFromString() throws Exception {
        ThingEdgeEventType.fromString(" ");
        assertTrue("IllegalArgumentException expected.", Boolean.FALSE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhitespaceFromString() throws Exception {
        ThingEdgeEventType.fromString(" \t");
        assertTrue("IllegalArgumentException expected.", Boolean.FALSE);
    }
}
