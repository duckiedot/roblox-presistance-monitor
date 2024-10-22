package com.presence.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.presence.adapter.RobloxPresenceStatusAdapter;
import com.presence.feign.RobloxPresenceFeignClient;
import com.presence.feign.RobloxUserFeignClient;
import com.presence.model.roblox.presence.RobloxPresenceStatus;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
  @Bean
  public RobloxPresenceFeignClient getRobloxPresenceFeignClient() {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(RobloxPresenceStatus.class, new RobloxPresenceStatusAdapter())
            .create();

    return Feign.builder()
        .encoder(new GsonEncoder(gson))
        .decoder(new GsonDecoder(gson))
        .target(RobloxPresenceFeignClient.class, "https://presence.roblox.com/v1/presence");
  }

  @Bean
  public RobloxUserFeignClient getRobloxUserFeignClient() {
    return Feign.builder()
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .target(RobloxUserFeignClient.class, "https://users.roblox.com");
  }
}
