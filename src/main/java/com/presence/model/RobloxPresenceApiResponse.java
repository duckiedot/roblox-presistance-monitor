package com.presence.model;

import java.util.Set;

import lombok.Data;

@Data
public class RobloxPresenceApiResponse {
    private Set<UserPresence> userPresences;
}
