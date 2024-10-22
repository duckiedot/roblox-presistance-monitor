package com.presence.model;

import java.util.Set;

public record AccountConfig(Set<Long> accountsToMonitor, String discordHook, long discordUserId) {}
