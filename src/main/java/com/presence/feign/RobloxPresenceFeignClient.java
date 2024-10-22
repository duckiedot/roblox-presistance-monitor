package com.presence.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.presence.model.RobloxPresenceApiResponse;
import com.presence.model.RobloxPresenceRequest;

import feign.Headers;
import feign.RequestLine;

@FeignClient(name = "robloxPresenceClient", url = "https://presence.roblox.com/v1/presence")
public interface RobloxPresenceFeignClient {
    @Headers({
            "Content-Type: application/json"
    })
    @RequestLine("POST /users")
    RobloxPresenceApiResponse getUsersPresence(RobloxPresenceRequest presenceRequest);
}
