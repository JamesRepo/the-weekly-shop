package com.jamesrepo.theweeklyshop.service;

import java.util.UUID;

public interface PersistenceService<Q, C> {

    Q get(UUID id);

    Q post(C command);

    Q put(UUID id, C command);

    UUID delete(UUID id);
}
