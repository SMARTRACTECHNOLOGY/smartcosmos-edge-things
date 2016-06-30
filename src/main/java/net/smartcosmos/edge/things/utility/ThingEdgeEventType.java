package net.smartcosmos.edge.things.utility;

import org.apache.commons.lang.StringUtils;

/**
 * An Enum defining the events reported by the Metadata service.
 */
public enum ThingEdgeEventType {
    THING_EDGE_CREATED("thing-edge:created"),
    THING_EDGE_UPDATED("thing-edge:updated"),
    THING_EDGE_UPSERTED("thing-edge:upserted"),
    THING_EDGE_READ("thing-edge:read"),
    THING_EDGE_DEACTIVATED("thing-edge:deactivated"),
    THING_EDGE_DELETED("thing-edge:deleted"),
    THING_EDGE_NOT_FOUND("thing-edge:notFound"),
    THING_EDGE_EMPTY_REQUEST("thing-edge:emptyRequest"),
    THING_EDGE_CONSTRAINT_VIOLATION("thing-edge:constraintViolation"),
    THING_EDGE_CREATE_FAILED_ALREADY_EXISTS("thing-edge:createFailedAlreadyExists"),
    UNKNOWN("thing-edge:unknown");

    private String eventName;

    private ThingEdgeEventType(String anEventName) {
        eventName = anEventName;
    }

    private String eventName() {
        return eventName;
    }

    /**
     * Get the event name that matches the provided String.
     *
     * @param anEventName hte text representation for the key type
     * @return the {@code ThingEdgeEventType} that matches the String provided, {@code ThingEdgeEventType.UNKNOWN} if no match
     * @throws IllegalArgumentException If a null, blank or whitespace only String is provided
     */
    public static ThingEdgeEventType fromString(String anEventName) {
        if (StringUtils.isBlank(anEventName)) {
            throw new IllegalArgumentException("Invalid value for event name.  Null, blank and whitespace are not allowed. value: '" +
                                               anEventName + "'.");
        }

        for (ThingEdgeEventType eventType : ThingEdgeEventType.values()) {
            if (eventType.eventName().equalsIgnoreCase(anEventName)) {
                return eventType;
            }
        }

        return UNKNOWN;
    }

    public String getEventName() {
        return eventName;
    }

}
