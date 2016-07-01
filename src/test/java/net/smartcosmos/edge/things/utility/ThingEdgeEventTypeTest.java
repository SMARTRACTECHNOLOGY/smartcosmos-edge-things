package net.smartcosmos.edge.things.utility;

import org.junit.*;

import static org.junit.Assert.*;

public class ThingEdgeEventTypeTest {
    @Test
    public void testCreatedFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_CREATED;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:created");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testUpdatedFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_UPDATED;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:updated");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testUpsertedFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_UPSERTED;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:upserted");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testReadFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_READ;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:read");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testDeactivatedFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_DEACTIVATED;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:deactivated");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testDeletedFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_DELETED;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:deleted");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testNotFoundFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_NOT_FOUND;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:notFound");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testEmptyRequestFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_EMPTY_REQUEST;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:emptyRequest");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testConstraintViolationFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_CONSTRAINT_VIOLATION;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:constraintViolation");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

    @Test
    public void testAlreadyExistsFromString() throws Exception {
        ThingEdgeEventType expected = ThingEdgeEventType.THING_EDGE_CREATE_FAILED_ALREADY_EXISTS;
        ThingEdgeEventType received = ThingEdgeEventType.fromString("thing-edge:createFailedAlreadyExists");
        assertTrue("Expected enum not created.  Received: " + received + ", expected: " + expected + ".",
                   expected.equals(received));
    }

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
