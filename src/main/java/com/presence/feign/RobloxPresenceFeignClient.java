package com.presence.feign;

import com.presence.model.roblox.presence.RobloxPresenceApiResponse;
import com.presence.model.roblox.presence.RobloxPresenceRequest;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "robloxPresenceClient", url = "https://presence.roblox.com/v1/presence")
public interface RobloxPresenceFeignClient {
  @Headers({"Content-Type: application/json"})
  @RequestLine("POST /users")
  RobloxPresenceApiResponse getUsersPresence(RobloxPresenceRequest presenceRequest);
}
