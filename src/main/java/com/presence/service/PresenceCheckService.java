package com.presence.service;

import com.presence.feign.RobloxPresenceFeignClient;
import com.presence.feign.RobloxUserFeignClient;
import com.presence.model.roblox.presence.RobloxPresenceApiResponse;
import com.presence.model.roblox.presence.RobloxPresenceRequest;
import com.presence.model.roblox.presence.RobloxPresenceStatus;
import com.presence.model.roblox.presence.UserPresence;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PresenceCheckService {
  @Qualifier("getRobloxPresenceFeignClient")
  private final RobloxPresenceFeignClient robloxApi;

  @Qualifier("getRobloxUserFeignClient")
  private final RobloxUserFeignClient userClient;

  /**
   * Retrieves and groups the user presences by their status.
   *
   * <p>This method fetches the presence information for a given set of account IDs from the Roblox
   * API and groups the results based on their presence status. Each status maps to a set of
   * corresponding display names of the users.
   *
   * @param accountIds a set of Roblox account IDs to check the presence for
   * @return a map where keys are {@link RobloxPresenceStatus} and values are sets of user display
   *     names
   */
  public Map<RobloxPresenceStatus, Set<String>> getGroupedUserPresences(
      final Set<Long> accountIds) {
    RobloxPresenceApiResponse robloxPresenceApiResponse =
        robloxApi.getUsersPresence(new RobloxPresenceRequest(accountIds));
    return robloxPresenceApiResponse.getUserPresences().stream()
        .collect(
            Collectors.groupingBy(
                UserPresence::getUserPresenceType,

                // Transform the grouped results into sets of display names
                Collectors.mapping(
                    userPresence ->
                        userClient
                            .getRobloxUsername(String.valueOf(userPresence.getUserId()))
                            .displayName(),
                    Collectors.toSet())));
  }
}
