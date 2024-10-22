package com.presence.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.presence.feign.RobloxPresenceFeignClient;
import com.presence.feign.RobloxUserFeignClient;
import com.presence.model.roblox.presence.RobloxPresenceApiResponse;
import com.presence.model.roblox.presence.RobloxPresenceStatus;
import com.presence.model.roblox.presence.UserPresence;
import com.presence.model.roblox.user.RobloxUsername;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PresenceCheckServiceTest {

  @Mock private RobloxPresenceFeignClient robloxApi;

  @Mock private RobloxUserFeignClient userClient;

  @InjectMocks private PresenceCheckService yourClass;

  @Test
  void testGetGroupedUserPresences() {
    Set<Long> accountIds = new HashSet<>();
    accountIds.add(1L);
    accountIds.add(2L);

    UserPresence presence1 = new UserPresence(RobloxPresenceStatus.ONLINE, null, 1L, null);
    UserPresence presence2 = new UserPresence(RobloxPresenceStatus.OFFLINE, null, 2L, null);
    RobloxPresenceApiResponse response = mock(RobloxPresenceApiResponse.class);

    when(robloxApi.getUsersPresence(any())).thenReturn(response);
    when(response.getUserPresences()).thenReturn(Set.of(presence1, presence2));

    when(userClient.getRobloxUsername("1"))
        .thenReturn(new RobloxUsername("user-1", "PlayerOne", 1L));
    when(userClient.getRobloxUsername("2"))
        .thenReturn(new RobloxUsername("user-2", "PlayerTwo", 2L));

    Map<RobloxPresenceStatus, Set<String>> result = yourClass.getGroupedUserPresences(accountIds);

    Map<RobloxPresenceStatus, Set<String>> expected = new HashMap<>();
    expected.put(RobloxPresenceStatus.ONLINE, Set.of("PlayerOne"));
    expected.put(RobloxPresenceStatus.OFFLINE, Set.of("PlayerTwo"));

    assertEquals(expected, result);
  }
}
