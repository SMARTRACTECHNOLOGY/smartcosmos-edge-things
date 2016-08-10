package net.smartcosmos.edge.things.service;

import net.smartcosmos.edge.things.utility.ThingEdgeEventType;
import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * A service that sends message to the event service.
 */
public interface EventSendingService<T> {

    /**
     * Send the event using {@link ThingEdgeEventType}.
     *
     * @param user the user making the request
     * @param eventType the event type
     * @param entity the object being reported
     */
    void sendEvent(SmartCosmosUser user, ThingEdgeEventType eventType, T entity);

    void sendEvent(SmartCosmosUser user, String eventType, T entity);

}
