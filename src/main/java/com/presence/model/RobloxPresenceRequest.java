package com.presence.model;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

public record RobloxPresenceRequest(Set<Long> userIds) {
}
