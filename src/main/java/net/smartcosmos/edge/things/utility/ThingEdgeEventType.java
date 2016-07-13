package net.smartcosmos.edge.things.utility;

import org.apache.commons.lang.StringUtils;

/**
 * An Enum defining the events reported by the Thing edge service.
 */
public enum ThingEdgeEventType {

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
