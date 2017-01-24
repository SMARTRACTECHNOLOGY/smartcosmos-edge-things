package net.smartcosmos.edge.things.service.things;

import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * Defines the service for the REST endpoint used to get Things.
 */
public interface GetThingRestService {

    /**
     * <p>Look up a Thing by Type and URN.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param type the type of the Thing
     * @param urn the URN of the Thing
     * @param user the user making the request
     * @return the response entity
     */
    @Retryable
    ResponseEntity<?> findByTypeAndUrn(String type, String urn, SmartCosmosUser user);

    /**
     * <p>Look up a Thing by Type and URN.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param type the type of the Things being sought
     * @param urns the set of URNs of the Things being sought
     * @param user the user making the request
     * @return the response entity
     */
    @Retryable
    ResponseEntity<?> findByTypeAndUrns(String type, Map<String, Set<String>> urns, SmartCosmosUser user);

    /**
     * <p>Look up Things by Type.</p>
     * <p>If the request fails, it is retried 3 times.</p>
     *
     * @param type the type of the Things
     * @param page the number of the results page
     * @param size the size of a results page
     * @param sortOrder order to sort the result, can be {@code ASC} or {@code DESC}
     * @param sortBy name of the field to sort by
     * @param user the user making the request
     * @return the response entity with the paged response
     */
    @Retryable
    ResponseEntity findByType(String type, Integer page, Integer size, String sortOrder, String sortBy, SmartCosmosUser user);
}
