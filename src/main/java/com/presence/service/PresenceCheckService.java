package com.presence.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.presence.feign.RobloxPresenceFeignClient;
import com.presence.model.RobloxPresenceApiResponse;
import com.presence.model.RobloxPresenceRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresenceCheckService {
    @Qualifier("getRobloxPresenceFeignClient")
    private final RobloxPresenceFeignClient robloxApi;

    public RobloxPresenceApiResponse checkPresenceStatus(final Set<Long> accountIds) {
        return robloxApi.getUsersPresence(new RobloxPresenceRequest(accountIds));
    }
}
