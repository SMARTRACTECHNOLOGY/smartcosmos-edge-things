package net.smartcosmos.edge.things.resource;

/**
 * A constant interface class for the Things edge endpoints.
 */
public interface ThingEdgeEndpointConstants {

    // region Base Constants

    String ENDPOINT_BASE_NAME_THINGS = "things";

    String ENDPOINT_ENABLEMENT_PREFIX = "smartcosmos.endpoints.edge";
    String ENDPOINT_ENABLEMENT_PROPERTY_ENABLED = "enabled";
    String ENDPOINT_ENABLEMENT_THINGS = ENDPOINT_ENABLEMENT_PREFIX + "." + ENDPOINT_BASE_NAME_THINGS;

    // endregion

    // region Path Segment and Parameter Constants

    String TYPE = "type";
    String URN = "urn";

    String FIND_BY_URNS = "/findByUrns";

    String PARAM_FORCE = "force";
    String PARAM_FIELDS = "fields";
    String PARAM_PAGE = "page";
    String PARAM_SIZE = "size";
    String PARAM_SORT_ORDER = "sortOrder";
    String PARAM_SORT_BY = "sortBy";

    // endregion

    // region Resource Paths

    String ENDPOINT_BASE = "/";
    String ENDPOINT_TYPE = ENDPOINT_BASE + "{" + TYPE + "}";
    String ENDPOINT_TYPE_URNS = ENDPOINT_BASE + "{" + TYPE + "}" + FIND_BY_URNS;
    String ENDPOINT_TYPE_URN = ENDPOINT_TYPE + "/{" + URN + "}";

    // endregion

    // region Endpoint Enablement

    String ENDPOINT_ENABLEMENT_THINGS_CREATE = ENDPOINT_ENABLEMENT_THINGS + ".create";

    String ENDPOINT_ENABLEMENT_THINGS_DELETE = ENDPOINT_ENABLEMENT_THINGS + ".delete";

    String ENDPOINT_ENABLEMENT_THINGS_READ = ENDPOINT_ENABLEMENT_THINGS + ".read";
    String ENDPOINT_ENABLEMENT_THINGS_READ_TYPE = ENDPOINT_ENABLEMENT_THINGS_READ + "." + TYPE;
    String ENDPOINT_ENABLEMENT_THINGS_READ_URN = ENDPOINT_ENABLEMENT_THINGS_READ + "." + URN;

    String ENDPOINT_ENABLEMENT_THINGS_UPDATE = ENDPOINT_ENABLEMENT_THINGS + ".update";

    // endregion
}
