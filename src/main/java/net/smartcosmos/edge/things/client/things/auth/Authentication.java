package net.smartcosmos.edge.things.client.things.auth;

import java.util.List;
import java.util.Map;

import net.smartcosmos.edge.things.client.things.Pair;

public interface Authentication {
    /** Apply authentication settings to header and query params. */
    void applyToParams(List<Pair> queryParams, Map<String, String> headerParams);
}
