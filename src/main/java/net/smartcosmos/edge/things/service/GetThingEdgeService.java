package net.smartcosmos.edge.things.service;

import org.springframework.http.ResponseEntity;

import net.smartcosmos.security.user.SmartCosmosUser;

public interface GetThingEdgeService {

    ResponseEntity<?> getAll(SmartCosmosUser user, Integer page, Integer size, String sortOrder, String sortBy);
}
