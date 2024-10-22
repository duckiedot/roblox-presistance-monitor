package com.presence.feign;

import com.presence.model.roblox.user.RobloxUsername;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "RobloxUserClient", url = "https://users.roblox.com")
public interface RobloxUserFeignClient {

  @RequestLine("GET /v1/users/{robloxUserId}")
  RobloxUsername getRobloxUsername(@Param("robloxUserId") String robloxUserId);
}
