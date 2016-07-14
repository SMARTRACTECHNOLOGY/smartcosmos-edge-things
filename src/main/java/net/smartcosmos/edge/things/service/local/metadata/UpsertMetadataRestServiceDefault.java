package net.smartcosmos.edge.things.service.local.metadata;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.smartcosmos.security.user.SmartCosmosUser;

/**
 * The default implementation to call the REST Metadata endpoint to upsert metadata.
 */
@Slf4j
@Service
public class UpsertMetadataRestServiceDefault implements UpsertMetadataRestService {

    @Override
    public ResponseEntity<?> upsert(
        String ownerType, String ownerUrn, Map<String, Object> metadataMap, SmartCosmosUser user) {
        return null;
    }
}
